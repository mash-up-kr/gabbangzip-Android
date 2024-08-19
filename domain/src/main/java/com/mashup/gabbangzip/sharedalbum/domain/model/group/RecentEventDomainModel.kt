package com.mashup.gabbangzip.sharedalbum.domain.model.group

import java.time.LocalDateTime

data class RecentEventDomainModel(
    val id: Long,
    val title: String?,
    val date: LocalDateTime?,
    val deadline: LocalDateTime?,
)
