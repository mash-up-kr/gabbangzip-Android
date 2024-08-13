package com.mashup.gabbangzip.sharedalbum.domain.model.vote

data class VoteResultParam(
    val eventId: Long,
    val voteResultIdList: List<Long>,
)
