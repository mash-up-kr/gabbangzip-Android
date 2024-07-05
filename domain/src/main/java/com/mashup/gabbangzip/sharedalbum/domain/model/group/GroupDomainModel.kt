package com.mashup.gabbangzip.sharedalbum.domain.model.group

data class GroupDomainModel(
    val id: Long,
    val cardBackImages: List<CardBackImageDomainModel>,
    val cardFrontImageUrl: String,
    val keyword: String,
    val name: String,
    val recentEventDate: String,
    val status: String,
    val statusDescription: String,
)
