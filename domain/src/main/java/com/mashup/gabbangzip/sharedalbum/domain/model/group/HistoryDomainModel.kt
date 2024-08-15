package com.mashup.gabbangzip.sharedalbum.domain.model.group

import java.time.LocalDateTime

data class HistoryDomainModel(
    val id: Long,
    val name: String,
    val date: LocalDateTime,
    val images: List<CardBackImageDomainModel>,
)
