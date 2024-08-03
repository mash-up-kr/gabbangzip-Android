package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.RecentEventDomainModel

data class GroupEvent(
    val title: String,
    val date: String,
    val deadline: String,
)

fun RecentEventDomainModel.toUiModel(): GroupEvent {
    return GroupEvent(
        title = title,
        date = date.toString(),
        deadline = deadline?.toString().orEmpty(),
    )
}
