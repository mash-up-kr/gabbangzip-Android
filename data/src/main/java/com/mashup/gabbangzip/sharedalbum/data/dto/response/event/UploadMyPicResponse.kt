package com.mashup.gabbangzip.sharedalbum.data.dto.response.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadMyPicResponse(
    @Json(name = "event_id") val eventId: Long,
)

fun UploadMyPicResponse.toDomainModel(): UploadMyPicDomainModel {
    return UploadMyPicDomainModel(eventId = eventId)
}
