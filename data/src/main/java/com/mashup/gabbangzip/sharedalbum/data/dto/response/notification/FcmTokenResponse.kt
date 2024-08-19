package com.mashup.gabbangzip.sharedalbum.data.dto.response.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FcmTokenResponse(
    @Json(name = "registered_token") val token: String,
)

fun FcmTokenResponse.toDomainModel(): FcmTokenDomainModel = FcmTokenDomainModel(
    fcmRegisteredToken = token,
)
