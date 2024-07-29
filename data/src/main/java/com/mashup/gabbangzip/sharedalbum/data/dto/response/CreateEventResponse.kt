package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateEventResponse(
    @Json(name = "event_date")
    val eventDate: String,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "summary")
    val summary: String,
    @Json(name = "upload_due_date")
    val uploadDueDate: String,
)