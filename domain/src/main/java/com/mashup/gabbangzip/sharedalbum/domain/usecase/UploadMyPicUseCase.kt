package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadImagesDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadImagesParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import dagger.Reusable
import java.io.File
import java.io.IOException
import javax.inject.Inject

@Reusable
class UploadMyPicUseCase @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(
        eventId: Long,
        fileList: List<File>,
    ): Result<UploadImagesDomainModel> {
        return runCatching {
            val pictureList = mutableListOf<String>()
            fileList.forEach { file ->
                uploadImageUseCase(file)
                    .onSuccess { fileId -> pictureList.add(fileId) }
                    .onFailure { throw IOException(it.message) }
            }
            eventRepository.uploadImages(
                UploadImagesParam(eventId, pictureList),
            )
        }
    }
}
