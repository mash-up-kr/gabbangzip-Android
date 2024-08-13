package com.mashup.gabbangzip.sharedalbum.data.dto.request.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.KookNotificationParamDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KookNotificationRequest(
    @Json(name = "event_id")
    val eventId: Long,
)

fun KookNotificationParamDomainModel.toRequestBody() =
    KookNotificationRequest(
        eventId = eventId,
    )
