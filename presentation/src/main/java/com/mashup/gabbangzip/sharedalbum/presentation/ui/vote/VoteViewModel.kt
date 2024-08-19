package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LoadUserInfoUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.vote.GetVotePhotoListUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.vote.GetVoteVisitUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.vote.RequestVoteResultUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.vote.SaveVoteVisitUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VotePhoto
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val requestVoteResultUseCase: RequestVoteResultUseCase,
    private val getVotePhotoListUseCase: GetVotePhotoListUseCase,
    private val getLoadUserInfoUseCase: LoadUserInfoUseCase,
    private val getVoteVisitUseCase: GetVoteVisitUseCase,
    private val saveVoteVisitUseCase: SaveVoteVisitUseCase,
) : ViewModel() {
    private val _voteUiState = MutableStateFlow(PhotoVoteState())
    val voteUiState = _voteUiState.asStateFlow()

    private val likedPhotoList = mutableListOf<VotePhoto>()

    init {
        checkFirstVisit()
    }

    private fun checkFirstVisit() {
        val isFirstVisit = getVoteVisitUseCase()
        _voteUiState.update { state ->
            state.copy(isFirstVisit = isFirstVisit)
        }

        if (isFirstVisit) {
            saveVoteVisitUseCase(isFirstVisit = false)
        }
    }

    fun fetchVotePhotoList(eventIdKey: String) {
        savedStateHandle.get<Long>(eventIdKey)?.let { eventId ->
            viewModelScope.launch {
                getVotePhotoListUseCase(eventId).onSuccess { votePhotoList ->
                    _voteUiState.update { state ->
                        state.copy(
                            photoList = ImmutableList(votePhotoList.toUiModel()),
                            voteResult = state.voteResult.copy(
                                eventId = eventId,
                            ),
                            voteClickInfo = state.voteClickInfo.copy(
                                index = votePhotoList.size,
                            ),
                        )
                    }
                }.onFailure {
                    updateErrorState()
                }
            }
        } ?: updateErrorState()
    }

    fun fetchUserInfo() {
        viewModelScope.launch {
            val userInfo = getLoadUserInfoUseCase().toUiModel()
            _voteUiState.update { state ->
                state.copy(userInfo = userInfo)
            }
        }
    }

    fun updateVoteEvent(type: PhotoVoteType) {
        val index = voteUiState.value.voteClickInfo.index - 1

        _voteUiState.update { state ->
            state.copy(
                voteClickInfo = state
                    .voteClickInfo
                    .copy(
                        index = index,
                        type = type,
                    ),
            )
        }
    }

    fun updateVoteDialog(isVisible: Boolean) {
        _voteUiState.update { state ->
            state.copy(isVoteCancel = isVisible)
        }
    }

    fun addVoteResult(voteType: PhotoVoteType, photo: VotePhoto) {
        if (voteType == PhotoVoteType.LIKE) {
            likedPhotoList.add(photo)
        }
    }

    fun finishVote() {
        viewModelScope.launch {
            requestVoteResultUseCase(
                VoteResultParam(
                    eventId = voteUiState.value.voteResult.eventId,
                    voteResultIdList = voteUiState.value.photoList.map { it.id },
                ),
            ).onSuccess { voteResultDomain ->
                _voteUiState.update { state ->
                    state.copy(
                        voteResult = voteResultDomain.toUiModel(),
                        isVoteUploadFinish = true,
                    )
                }
            }.onFailure {
                _voteUiState.update { state ->
                    state.copy(
                        isVoteUploadFinish = false,
                    )
                }
            }
        }
    }

    private fun updateErrorState() {
        _voteUiState.update { state ->
            state.copy(
                isError = true,
            )
        }
    }
}
