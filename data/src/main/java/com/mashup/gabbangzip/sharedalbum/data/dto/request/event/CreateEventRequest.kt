package com.mashup.gabbangzip.sharedalbum.data.dto.request.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateEventRequest(
    @Json(name = "group_id")
    val groupId: Long,
    @Json(name = "date")
    val date: String,
    @Json(name = "pictures")
    val pictures: List<String>,
    @Json(name = "description")
    val description: String,
)
