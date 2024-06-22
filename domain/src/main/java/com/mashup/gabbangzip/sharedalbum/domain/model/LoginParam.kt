package com.mashup.gabbangzip.sharedalbum.domain.model

data class LoginParam(
    val idToken: String,
    val provider: String = "KAKAO",
    val nickname: String,
    val profileImage: String,
)
