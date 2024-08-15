package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.CreateEventRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.toRequestBody
import com.mashup.gabbangzip.sharedalbum.data.dto.response.event.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.UploadMyPicRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.event.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.EventService
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventService: EventService,
) : EventRepository {
    override suspend fun createEvent(param: EventCreationParam): EventCreationDomainModel {
        val request = CreateEventRequest(
            groupId = param.groupId,
            description = param.description,
            date = param.date,
            pictures = param.pictures,
        )
        return callApi { eventService.createEvent(request) }.toDomainModel()
    }

    override suspend fun uploadMyPic(param: UploadMyPicParam): UploadMyPicDomainModel {
        val request = UploadMyPicRequest(
            eventId = param.eventId,
            imageUrls = param.imageUrls,
        )
        return callApi { eventService.uploadMyPic(request) }.toDomainModel()
    }

    override suspend fun checkVisitEvent(param: EventVisitParamDomainModel): EventVisitDomainModel {
        return callApi { eventService.checkVisitEvent(param.toRequestBody()) }.toDomainModel()
    }
}
