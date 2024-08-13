package com.mashup.gabbangzip.sharedalbum.data.dto.response.vote

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VoteResultResponse(
    @Json(name = "event_id")
    val eventId: Long,
    @Json(name = "random_image_url")
    val randomImageUrl: String,
    @Json(name = "group_keyword")
    val groupKeyword: String,
)

fun VoteResultResponse.toDomainModel(): VoteResultDomainModel =
    VoteResultDomainModel(
        eventId = eventId,
        randomImageUrl = randomImageUrl,
        groupKeyword = groupKeyword,
    )
