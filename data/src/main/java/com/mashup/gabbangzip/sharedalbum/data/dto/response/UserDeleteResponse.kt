package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDeleteResponse(
    @Json(name = "user_id")
    val userId: Long,
)
