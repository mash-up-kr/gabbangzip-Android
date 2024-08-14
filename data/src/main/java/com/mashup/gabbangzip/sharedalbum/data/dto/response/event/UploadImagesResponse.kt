package com.mashup.gabbangzip.sharedalbum.data.dto.response.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadImagesResponse(
    @Json(name = "event_id") val eventId: Long,
)
