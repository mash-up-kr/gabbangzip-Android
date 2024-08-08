package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberDomainModel

data class Member(
    val id: Long,
    val name: String,
)

fun MemberDomainModel.toUiModel(): Member {
    return Member(
        id = id,
        name = nickname,
    )
}
