package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class VoteResultUiParam(
    val eventId: Long,
    val voteResultIdList: ImmutableList<Long>,
)

fun VoteResultUiParam.toDomainParam(): VoteResultParam {
    return VoteResultParam(
        eventId = eventId,
        voteResultIdList = voteResultIdList.toList(),
    )
}
