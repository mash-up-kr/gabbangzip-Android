package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val userId: Long,
    val nickname: String,
    val accessToken: String,
    val refreshToken: String,
)
