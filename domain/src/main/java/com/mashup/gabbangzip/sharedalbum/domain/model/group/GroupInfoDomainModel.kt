package com.mashup.gabbangzip.sharedalbum.domain.model.group

data class GroupInfoDomainModel(
    val id: Long,
    val name: String,
    val keyword: String,
    val imageUrl: String,
    val invitationUrl: String,
)
