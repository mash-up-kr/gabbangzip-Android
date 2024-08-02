package com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation

data class EventCreationParam(
    val summary: String,
    val date: String,
    val pictures: List<String>,
)
