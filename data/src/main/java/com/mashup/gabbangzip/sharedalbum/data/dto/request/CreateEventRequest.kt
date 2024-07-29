package com.mashup.gabbangzip.sharedalbum.data.dto.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateEventRequest(
    @Json(name = "event_date")
    val eventDate: String,
    @Json(name = "pictures")
    val pictures: List<String>,
    @Json(name = "summary")
    val summary: String,
)
