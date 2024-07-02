package com.mashup.gabbangzip.sharedalbum.data.dto.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenRefreshRequest(
    @Json(name = "refresh_token")
    val refreshToken: String,
)
