package com.mashup.gabbangzip.sharedalbum.data.dto.response.vote

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VoteResultResponse(
    @Json(name = "event_id")
    val eventId: Long,
)

fun VoteResultResponse.toDomainModel(): VoteResultDomainModel =
    VoteResultDomainModel(eventId = eventId)
