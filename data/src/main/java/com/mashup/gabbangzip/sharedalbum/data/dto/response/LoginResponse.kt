package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "userId")
    val userId: Long,
    @Json(name = "nickname")
    val nickname: String,
    @Json(name = "accessToken")
    val accessToken: String,
    @Json(name = "refreshToken")
    val refreshToken: String,
)
