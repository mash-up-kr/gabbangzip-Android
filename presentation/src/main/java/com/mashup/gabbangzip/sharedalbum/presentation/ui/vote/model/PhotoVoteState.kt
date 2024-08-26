package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.UserInfo
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class PhotoVoteState(
    val photoList: ImmutableList<VotePhoto> = ImmutableList(emptyList()),
    val userInfo: UserInfo = UserInfo(),
    val voteResult: VoteResult = VoteResult(),
    val voteClickInfo: VoteClickInfo = VoteClickInfo(),
    val isVoteCancel: Boolean = false,
    val isLoading: Boolean = false,
    val isVoteUploadFinish: Boolean? = null,
    val isError: Boolean = false,
    val isFirstVisit: Boolean = false,
)
