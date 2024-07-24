package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.lifecycle.ViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VoteViewModel @Inject constructor() : ViewModel() {
    private val _voteUiState = MutableStateFlow(PhotoVoteState())
    val voteUiState = _voteUiState.asStateFlow()

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
        // TODO: api 스펙 정의되면 리스트에 저장하는 로직 구현하기
    }

    fun finishVote() {
        // TODO: api 스펙 정의되면 리스트를 서버에 보내는 로직 구현하기
    }
}
