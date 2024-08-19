package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.RecentEventDomainModel
import com.mashup.gabbangzip.sharedalbum.presentation.utils.LocalDateUtil

data class GroupEvent(
    val id: Long,
    val title: String,
    val date: String,
    val deadline: String,
)

fun RecentEventDomainModel.toUiModel(): GroupEvent {
    return GroupEvent(
        id = id,
        title = title.orEmpty(),
        date = date?.let { LocalDateUtil.format(it) }.orEmpty(),
        deadline = deadline?.let {
            LocalDateUtil.format(it, "M월 d일 EEEE hh시 mm분") + " PIC 종료"
        }.orEmpty(),
    )
}
