package com.mashup.gabbangzip.sharedalbum.domain.model.event

data class UploadMyPicParam(
    val eventId: Long,
    val imageUrls: List<String>,
)
