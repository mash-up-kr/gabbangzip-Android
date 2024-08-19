package com.mashup.gabbangzip.sharedalbum.domain.model.group

data class GroupDomainModel(
    val id: Long,
    val cardBackImages: List<CardBackImageDomainModel>,
    val cardFrontImageUrl: String,
    val keyword: String,
    val name: String,
    val recentEvent: RecentEventDomainModel,
    val status: String,
    val statusDescription: String,
    val history: List<HistoryDomainModel> = listOf(),
)
