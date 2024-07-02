package com.mashup.gabbangzip.sharedalbum.domain.model

import java.time.LocalDateTime

data class GroupDetailInfo(
    val name: String,
    val keyword: GroupKeyword,
    val status: String,
    val statusDescription: String,
    val updatedImages: Boolean,
    val piced: Boolean,
    val recentEventDate: LocalDateTime,
    val cardFrontImageUrl: String,
    val cardBackImages: List<ImageWithFrame>,
)
