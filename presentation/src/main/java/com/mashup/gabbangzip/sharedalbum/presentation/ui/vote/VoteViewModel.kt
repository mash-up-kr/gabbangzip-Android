package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.vote.RequestVoteResultUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VoteResultUiParam
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.toDomainParam
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteViewModel @Inject constructor(
    private val requestVoteResultUseCase: RequestVoteResultUseCase,
) : ViewModel() {
    private val _voteUiState = MutableStateFlow(PhotoVoteState())
    val voteUiState = _voteUiState.asStateFlow()

    private val likedPhotoList = mutableListOf<Photo>()

    fun updateVoteEvent(type: PhotoVoteType) {
        val index = voteUiState.value.voteClickInfo.index + 1

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

    fun addVoteResult(voteType: PhotoVoteType, photo: Photo) {
        if (voteType == PhotoVoteType.LIKE) {
            likedPhotoList.add(photo)
        }
    }

    fun finishVote() {
        viewModelScope.launch {
            // TODO: 나중에 예외 처리 방법 결정되면 onSuccess,onFailure 구현하기
            requestVoteResultUseCase(
                VoteResultUiParam(
                    eventId = voteUiState.value.eventId,
                    voteResultIdList = ImmutableList(voteUiState.value.photoList.map { it.id }),
                ).toDomainParam(),
            )
        }
    }
}
