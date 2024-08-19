package com.mashup.gabbangzip.sharedalbum.domain.model.event

data class EventCreationParam(
    val groupId: Long,
    val description: String,
    val date: String,
    val pictures: List<String>,
)
