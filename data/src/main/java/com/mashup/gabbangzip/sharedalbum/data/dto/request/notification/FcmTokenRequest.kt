package com.mashup.gabbangzip.sharedalbum.data.dto.request.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenParamDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FcmTokenRequest(
    @Json(name = "token")
    val fcmToken: String,
)

fun FcmTokenParamDomainModel.toRequestBody() = FcmTokenRequest(fcmToken = fcmToken)
