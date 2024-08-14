package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import dagger.Reusable
import java.io.File
import java.io.IOException
import javax.inject.Inject

@Reusable
class CreateEventUseCase @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(
        groupId: Long,
        description: String,
        date: String,
        fileList: List<File>,
    ): Result<EventCreationDomainModel> {
        return runCatching {
            val pictureList = mutableListOf<String>()
            fileList.forEach { file ->
                uploadImageUseCase(file)
                    .onSuccess { fileId -> pictureList.add(fileId) }
                    .onFailure { throw IOException(it.message) }
            }
            eventRepository.createEvent(
                EventCreationParam(groupId, description, date, pictureList),
            )
        }
    }
}
