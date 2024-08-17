package com.mashup.gabbangzip.sharedalbum.data.dto.request.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitParamDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventVisitRequest(
    @Json(name = "event_id")
    val eventId: Long,
)

fun EventVisitParamDomainModel.toRequestBody(): EventVisitRequest =
    EventVisitRequest(eventId = eventId)
