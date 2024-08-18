package com.mashup.gabbangzip.sharedalbum.data.dto.response.vote

import com.mashup.gabbangzip.sharedalbum.data.common.toS3Url
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VotePhotoDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VotePhotoOptionResponse(
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "option_id")
    val optionId: Long,
)

fun VotePhotoOptionResponse.toDomainModel(): VotePhotoDomainModel =
    VotePhotoDomainModel(imageUrl = imageUrl.toS3Url(), id = optionId)

fun List<VotePhotoOptionResponse>.toDomainModel(): List<VotePhotoDomainModel> =
    map { it.toDomainModel() }
