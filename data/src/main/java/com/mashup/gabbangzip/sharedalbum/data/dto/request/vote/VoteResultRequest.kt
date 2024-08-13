package com.mashup.gabbangzip.sharedalbum.data.dto.request.vote

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VoteResultRequest(
    @Json(name = "event_id")
    val eventId: Long,
    @Json(name = "liked_option_ids")
    val voteResultIdList: List<Long>,
)

fun VoteResultParam.toRequestBody(): VoteResultRequest =
    VoteResultRequest(
        eventId = eventId,
        voteResultIdList = voteResultIdList,
    )
