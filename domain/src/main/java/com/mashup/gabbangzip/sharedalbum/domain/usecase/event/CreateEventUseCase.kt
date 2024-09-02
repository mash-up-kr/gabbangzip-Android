package com.mashup.gabbangzip.sharedalbum.domain.usecase.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import com.mashup.gabbangzip.sharedalbum.domain.usecase.UploadImageListUseCase
import dagger.Reusable
import java.io.File
import javax.inject.Inject

@Reusable
class CreateEventUseCase @Inject constructor(
    private val uploadImageListUseCase: UploadImageListUseCase,
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(
        groupId: Long,
        description: String,
        date: String,
        fileList: List<File>,
    ): Result<EventCreationDomainModel> {
        return runCatching {
            val pictureList = uploadImageListUseCase(fileList).getOrThrow()

            eventRepository.createEvent(
                EventCreationParam(groupId, description, date, pictureList),
            )
        }
    }
}
