package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo

data class GroupDetailUiState(
    val isLoading: Boolean = false,
    val groupInfo: GroupInfo? = null,
    val recentEvent: GroupEvent? = null,
    val history: List<HistoryItem> = emptyList(),
    val error: String? = null,
)
