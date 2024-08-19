package com.mashup.gabbangzip.sharedalbum.data.dto.response.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateEventResponse(
    @Json(name = "id") val eventId: Long,
)

fun CreateEventResponse.toDomainModel(): EventCreationDomainModel {
    return EventCreationDomainModel(eventId = eventId)
}
