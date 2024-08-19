package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.data.common.toS3Url
import com.mashup.gabbangzip.sharedalbum.domain.model.group.CardBackImageDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardBackImageResponse(
    @Json(name = "frame")
    val frameType: String,
    @Json(name = "image_url")
    val imageUrl: String,
)

fun CardBackImageResponse.toDomainModel(): CardBackImageDomainModel {
    return CardBackImageDomainModel(
        frameType = frameType,
        imageUrl = imageUrl.toS3Url(),
    )
}
