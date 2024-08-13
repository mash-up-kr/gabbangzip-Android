package com.mashup.gabbangzip.sharedalbum.domain.usecase.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.KookNotificationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.KookNotificationParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.NotificationRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SendKookNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(kookParam: KookNotificationParamDomainModel): Result<KookNotificationDomainModel> {
        return runCatching {
            notificationRepository.sendKookNotification(kookParam)
        }
    }
}
