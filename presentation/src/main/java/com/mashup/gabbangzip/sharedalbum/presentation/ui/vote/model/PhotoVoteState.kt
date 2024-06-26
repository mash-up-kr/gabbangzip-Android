package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.UserInfo
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class PhotoVoteState(
    val photoList: ImmutableList<Photo>,
    val userInfo: UserInfo,
    val isLoading: Boolean,
)
