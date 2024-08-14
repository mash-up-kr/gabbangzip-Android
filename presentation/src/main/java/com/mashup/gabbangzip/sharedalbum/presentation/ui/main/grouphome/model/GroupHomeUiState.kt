package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

sealed class GroupHomeUiState {
    data object NotInitialized : GroupHomeUiState()
    data object Loading : GroupHomeUiState()
    data object NoGroup : GroupHomeUiState()
    data class GroupList(
        val groupList: ImmutableList<GroupInfo> = ImmutableList(emptyList()),
    ) : GroupHomeUiState()
}
