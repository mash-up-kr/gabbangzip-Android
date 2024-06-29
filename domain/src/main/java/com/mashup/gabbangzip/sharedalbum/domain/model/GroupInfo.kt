package com.mashup.gabbangzip.sharedalbum.domain.model

data class GroupInfo(
    val id: Long,
    val groupName: String,
    val keyword: GroupKeyWord?,
    val groupImageUrl: String,
    val groupInvitationUrl: String,
)
