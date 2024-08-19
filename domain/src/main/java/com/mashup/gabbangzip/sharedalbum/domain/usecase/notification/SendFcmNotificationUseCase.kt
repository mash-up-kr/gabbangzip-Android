package com.mashup.gabbangzip.sharedalbum.domain.usecase.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmNotificationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmNotificationParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.NotificationRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SendFcmNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(kookParam: FcmNotificationParamDomainModel): Result<FcmNotificationDomainModel> {
        return runCatching {
            notificationRepository.sendFcmNotification(kookParam)
        }
    }
}
