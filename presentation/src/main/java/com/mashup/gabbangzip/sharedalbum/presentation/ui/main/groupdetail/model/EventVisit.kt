package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitDomainModel

@JvmInline
value class EventVisit(
    val isVisit: Boolean,
)

fun EventVisitDomainModel.toUiModel(): EventVisit = EventVisit(isVisit = isVisit)
