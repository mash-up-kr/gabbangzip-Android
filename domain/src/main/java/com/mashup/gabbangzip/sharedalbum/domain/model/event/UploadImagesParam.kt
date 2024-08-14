package com.mashup.gabbangzip.sharedalbum.domain.model.event

data class UploadImagesParam(
    val eventId: Long,
    val imageUrls: List<String>,
)
