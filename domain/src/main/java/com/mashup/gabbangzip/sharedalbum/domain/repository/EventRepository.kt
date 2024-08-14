package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadImagesDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadImagesParam

interface EventRepository {
    suspend fun createEvent(param: EventCreationParam): EventCreationDomainModel
    suspend fun uploadImages(param: UploadImagesParam): UploadImagesDomainModel
}
