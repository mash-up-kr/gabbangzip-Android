package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType

data class GroupDetailUiState(
    val isLoading: Boolean = false,
    val groupInfo: GroupInfo? = null,
    val status: GroupStatusType = GroupStatusType.NO_CURRENT_EVENT,
    val recentEvent: GroupEvent? = null,
    val history: List<HistoryItem> = emptyList(),
    @StringRes val errorMessage: Int? = null,
) {
    val isEnabledNewEvent: Boolean
        get() = status == GroupStatusType.EVENT_COMPLETED || status == GroupStatusType.NO_CURRENT_EVENT
}
