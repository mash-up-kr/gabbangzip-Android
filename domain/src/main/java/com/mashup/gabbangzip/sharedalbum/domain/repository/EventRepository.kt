package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicParam
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationParam

interface EventRepository {
    suspend fun createEvent(param: EventCreationParam): EventCreationDomainModel
    suspend fun uploadMyPic(param: UploadMyPicParam): UploadMyPicDomainModel
    suspend fun checkVisitEvent(param: EventVisitParamDomainModel): EventVisitDomainModel
}
