package com.mashup.gabbangzip.sharedalbum.domain.model

data class GroupInfo(
    val id: Long,
    val name: String,
    val keyword: GroupKeyword?,
    val imageUrl: String,
    val invitationUrl: String,
)
