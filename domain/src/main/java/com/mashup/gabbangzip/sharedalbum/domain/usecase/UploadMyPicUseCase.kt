package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import dagger.Reusable
import java.io.File
import javax.inject.Inject

@Reusable
class UploadMyPicUseCase @Inject constructor(
    private val uploadImageListUseCase: UploadImageListUseCase,
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(
        eventId: Long,
        fileList: List<File>,
    ): Result<UploadMyPicDomainModel> {
        return runCatching {
            val pictureList = uploadImageListUseCase(fileList).getOrThrow()

            eventRepository.uploadMyPic(
                UploadMyPicParam(eventId, pictureList),
            )
        }
    }
}
