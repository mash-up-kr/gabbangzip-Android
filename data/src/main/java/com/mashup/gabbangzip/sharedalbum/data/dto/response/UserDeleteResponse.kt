package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDeleteResponse(
    @Json(name = "userId")
    val userId: Long,
)
