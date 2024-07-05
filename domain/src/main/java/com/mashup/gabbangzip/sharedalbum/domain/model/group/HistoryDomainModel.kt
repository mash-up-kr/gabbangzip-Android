package com.mashup.gabbangzip.sharedalbum.domain.model.group

data class HistoryDomainModel(
    val id: Long,
    val name: String,
    val date: String,
    val images: List<CardBackImageDomainModel>,
)
