package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateEventRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.EventService
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventService: EventService,
) : EventRepository {
    override suspend fun createEvent(param: EventCreationParam): EventCreationDomainModel {
        val request = CreateEventRequest(
            groupId = 0, // 변경 필요
            description = param.summary,
            date = param.date,
            pictures = param.pictures,
        )
        return callApi { eventService.createEvent(request) }.toDomainModel()
    }
}
