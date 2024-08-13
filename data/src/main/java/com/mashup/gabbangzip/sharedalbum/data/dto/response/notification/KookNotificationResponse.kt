package com.mashup.gabbangzip.sharedalbum.data.dto.response.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.KookNotificationDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KookNotificationResponse(
    @Json(name = "event_id")
    val eventId: Long,
)

fun KookNotificationResponse.toDomainModel(): KookNotificationDomainModel =
    KookNotificationDomainModel(eventId = eventId)
