package com.mashup.gabbangzip.sharedalbum.data.dto.request

import com.mashup.gabbangzip.sharedalbum.domain.model.firebase.FcmTokenParamDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FcmTokenRequest(
    @Json(name = "token")
    val fcmToken: String,
)

fun FcmTokenParamDomainModel.toRequest() = FcmTokenRequest(fcmToken = fcmToken)
