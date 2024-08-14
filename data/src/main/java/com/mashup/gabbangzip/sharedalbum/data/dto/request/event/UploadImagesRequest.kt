package com.mashup.gabbangzip.sharedalbum.data.dto.request.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadImagesRequest(
    @Json(name = "event_id")
    val eventId: Long,
    @Json(name = "image_urls")
    val imageUrls: List<String>,
)
