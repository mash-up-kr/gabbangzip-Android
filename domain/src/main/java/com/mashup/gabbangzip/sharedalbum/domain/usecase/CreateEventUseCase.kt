package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.eventcreation.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import java.io.File
import java.io.IOException
import javax.inject.Inject

class CreateEventUseCase @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(
        summary: String,
        date: String,
        fileList: List<File>,
    ): Result<EventCreationDomainModel> {
        return runCatching {
            val pictureList = mutableListOf<String>()
            fileList.forEach { file ->
                uploadImageUseCase(file)
                    .onSuccess { fileId ->
                        pictureList.add(fileId)
                    }
                    .onFailure {
                        throw IOException(it.message)
                    }
            }
            if (pictureList.size == fileList.size) {
                eventRepository.createEvent(EventCreationParam(summary, date, pictureList))
            } else {
                throw IOException("error!!!!!!!")
            }
        }
    }
}
