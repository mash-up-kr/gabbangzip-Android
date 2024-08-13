package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

data class VoteResult(
    val eventId: Long = 0,
    val randomImageUrl: String = "",
    val groupKeyword: GroupKeyword = GroupKeyword.SCHOOL,
)

fun VoteResultDomainModel.toUiModel(): VoteResult {
    return VoteResult(
        eventId = eventId,
        randomImageUrl = randomImageUrl,
        groupKeyword = GroupKeyword.getKeyword(groupKeyword),
    )
}
