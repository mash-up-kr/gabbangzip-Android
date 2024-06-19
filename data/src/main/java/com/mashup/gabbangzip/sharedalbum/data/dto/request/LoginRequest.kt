package com.mashup.gabbangzip.sharedalbum.data.dto.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "idToken")
    val idToken: String,
    @Json(name = "provider")
    val provider: String,
    @Json(name = "nickname")
    val nickname: String,
    @Json(name = "profileImage")
    val profileImage: String,
)
