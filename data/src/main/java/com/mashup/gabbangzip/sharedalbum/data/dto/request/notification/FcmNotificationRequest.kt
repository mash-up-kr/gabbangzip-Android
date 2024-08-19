package com.mashup.gabbangzip.sharedalbum.data.dto.request.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmNotificationParamDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FcmNotificationRequest(
    @Json(name = "event_id")
    val eventId: Long,
)

fun FcmNotificationParamDomainModel.toRequestBody() =
    FcmNotificationRequest(
        eventId = eventId,
    )
