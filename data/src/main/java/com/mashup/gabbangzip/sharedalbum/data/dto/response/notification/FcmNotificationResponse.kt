package com.mashup.gabbangzip.sharedalbum.data.dto.response.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmNotificationDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FcmNotificationResponse(
    @Json(name = "event_id")
    val eventId: Long,
)

fun FcmNotificationResponse.toDomainModel(): FcmNotificationDomainModel =
    FcmNotificationDomainModel(eventId = eventId)
