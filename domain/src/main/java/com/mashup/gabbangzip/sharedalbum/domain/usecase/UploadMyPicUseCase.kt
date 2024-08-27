package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.UploadMyPicParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.File
import javax.inject.Inject

@Reusable
class UploadMyPicUseCase @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(
        eventId: Long,
        fileList: List<File>,
    ): Result<UploadMyPicDomainModel> {
        return runCatching {
            coroutineScope {
                val pictureList = fileList.map { file ->
                    async(Dispatchers.IO) {
                        uploadImageUseCase(file).getOrThrow()
                    }
                }

                eventRepository.uploadMyPic(
                    UploadMyPicParam(eventId, pictureList.awaitAll()),
                )
            }
        }
    }
}
