package com.mashup.gabbangzip.sharedalbum.data.dto.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageWithFrame(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "frame_url")
    val frameUrl: String,
)
