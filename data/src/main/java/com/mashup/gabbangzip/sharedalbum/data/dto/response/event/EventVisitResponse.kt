package com.mashup.gabbangzip.sharedalbum.data.dto.response.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventVisitResponse(
    @Json(name = "visited")
    val isVisit: Boolean,
)

fun EventVisitResponse.toDomainModel(): EventVisitDomainModel =
    EventVisitDomainModel(isVisit = isVisit)
