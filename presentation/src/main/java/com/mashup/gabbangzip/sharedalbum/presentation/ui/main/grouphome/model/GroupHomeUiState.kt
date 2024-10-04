package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

sealed class GroupHomeUiState {
    data object NotInitialized : GroupHomeUiState()
    data object Loading : GroupHomeUiState()
    data object NoGroup : GroupHomeUiState()
    data class GroupList(
        val groupList: ImmutableList<GroupInfo> = ImmutableList(emptyList()),
        val filterTagList: ImmutableList<FilterTag> = ImmutableList(emptyList()),
        val viewType: ViewType = ViewType.List,
    ) : GroupHomeUiState()

    data class Error(@StringRes val errorMessage: Int) : GroupHomeUiState()
}
