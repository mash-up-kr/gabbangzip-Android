package com.mashup.gabbangzip.sharedalbum.domain.usecase.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventVisitParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CheckVisitUseCase @Inject constructor(
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(eventId: Long): Result<EventVisitDomainModel> {
        return runCatching {
            eventRepository.checkVisitEvent(EventVisitParamDomainModel(eventId))
        }
    }
}
