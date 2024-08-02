package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationParam

interface EventRepository {
    suspend fun createEvent(param: EventCreationParam): EventCreationDomainModel
}
