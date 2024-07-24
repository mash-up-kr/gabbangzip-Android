package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

data class VoteClickInfo(
    val index: Int = -1,
    val type: PhotoVoteType = PhotoVoteType.DEFAULT,
)
