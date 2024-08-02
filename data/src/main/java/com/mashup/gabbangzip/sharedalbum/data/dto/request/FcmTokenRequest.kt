package com.mashup.gabbangzip.sharedalbum.data.dto.request

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenParamDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@JvmInline
value class FcmTokenRequest(
    @Json(name = "token")
    val fcmToken: String,
)

fun FcmTokenParamDomainModel.toRequestBody() = FcmTokenRequest(fcmToken = fcmToken)
