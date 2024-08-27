package com.mashup.gabbangzip.sharedalbum.domain.usecase.event

import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.event.EventCreationParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import com.mashup.gabbangzip.sharedalbum.domain.usecase.UploadImageUseCase
import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.File
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
            coroutineScope {
                val pictureList = fileList.map { file ->
                    async(Dispatchers.IO) {
                        uploadImageUseCase(file).getOrThrow()
                    }
                }

                eventRepository.createEvent(
                    EventCreationParam(groupId, description, date, pictureList.awaitAll()),
                )
            }
        }
    }
}
