package com.mashup.gabbangzip.sharedalbum.data.dto.response.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadImagesDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadImagesResponse(
    @Json(name = "event_id") val eventId: Long,
)

fun UploadImagesResponse.toDomainModel(): UploadImagesDomainModel {
    return UploadImagesDomainModel(eventId = eventId)
}
