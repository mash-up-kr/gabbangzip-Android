package com.mashup.gabbangzip.sharedalbum.data.dto.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val idToken: String,
    val provider: String,
    val nickname: String,
    val profileImage: String,
)
