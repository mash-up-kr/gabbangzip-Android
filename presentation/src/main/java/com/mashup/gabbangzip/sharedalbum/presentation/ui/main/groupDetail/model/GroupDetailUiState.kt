package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model

import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class GroupDetailUiState(
    val groupId: Long,
    val recentEvent: GroupEvent? = null,
    val history: ImmutableList<HistoryItem>,
)
