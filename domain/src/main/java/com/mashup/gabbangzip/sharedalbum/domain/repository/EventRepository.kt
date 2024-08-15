package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicParam

interface EventRepository {
    suspend fun createEvent(param: EventCreationParam): EventCreationDomainModel
    suspend fun uploadMyPic(param: UploadMyPicParam): UploadMyPicDomainModel
}
