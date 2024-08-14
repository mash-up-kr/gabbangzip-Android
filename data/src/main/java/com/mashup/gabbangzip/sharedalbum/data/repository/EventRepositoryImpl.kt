package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.CreateEventRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.event.UploadImagesRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.event.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.EventService
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadImagesDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadImagesParam
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

    override suspend fun uploadImages(param: UploadImagesParam): UploadImagesDomainModel {
        val request = UploadImagesRequest(
            eventId = param.eventId,
            imageUrls = param.imageUrls,
        )
        return callApi { eventService.uploadImages(request) }.toDomainModel()
    }
}
