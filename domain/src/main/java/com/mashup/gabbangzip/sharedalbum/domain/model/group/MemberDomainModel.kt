package com.mashup.gabbangzip.sharedalbum.domain.model.group

data class MemberListDomainModel(
    val members: List<MemberDomainModel>,
    val invitationCode: String,
)

data class MemberDomainModel(
    val id: Long,
    val nickname: String,
)
