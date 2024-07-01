package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.UserInfo
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class GroupHomeUiState(
    val groupList: ImmutableList<GroupInfo> = ImmutableList(emptyList()),
    val user: UserInfo = UserInfo("", ""),
    val isLoading: Boolean = false,
)
