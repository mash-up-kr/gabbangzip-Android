package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

sealed class GroupHomeUiState {
    data object NotInitialized : GroupHomeUiState()
    data object Loading : GroupHomeUiState()
    data object NoGroup : GroupHomeUiState()
    data class GroupList(
        val groupList: ImmutableList<GroupInfo> = ImmutableList(emptyList()),
        val filterTagList: ImmutableList<FilterTagUiModel> = ImmutableList(
            listOf(FilterTagUiModel(GroupKeyword.TOTAL, R.drawable.sb_total, R.string.tag_total, true)) +
                GroupKeyword.entries.map { FilterTagUiModel(it.name, it.symbolResId, it.tagNameResId, false) },
        ),
    ) : GroupHomeUiState()
    data class Error(@StringRes val errorMessage: Int) : GroupHomeUiState()
}
