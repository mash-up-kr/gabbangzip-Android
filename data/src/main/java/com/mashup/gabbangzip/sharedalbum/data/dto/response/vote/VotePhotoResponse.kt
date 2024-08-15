package com.mashup.gabbangzip.sharedalbum.data.dto.response.vote

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VotePhotoDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VotePhotoResponse(
    @Json(name = "options")
    val options: List<VotePhotoOptionResponse>,
)

fun VotePhotoResponse.toDomainModel(): List<VotePhotoDomainModel> = options.toDomainModel()
