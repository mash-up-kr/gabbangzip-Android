package com.mashup.gabbangzip.sharedalbum.domain.usecase.notification

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.NotificationRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RegisterFcmTokenUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository,
) {
    suspend operator fun invoke(token: String): Result<FcmTokenDomainModel> {
        return runCatching {
            notificationRepository.registerFcmToken(FcmTokenParamDomainModel(token))
        }
    }
}
