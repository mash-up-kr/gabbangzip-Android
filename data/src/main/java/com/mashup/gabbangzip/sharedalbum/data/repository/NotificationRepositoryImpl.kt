package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.toRequestBody
import com.mashup.gabbangzip.sharedalbum.data.dto.response.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.NotificationService
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationService: NotificationService,
) : NotificationRepository {
    override suspend fun registerFcmToken(token: FcmTokenParamDomainModel): FcmTokenDomainModel {
        val response = callApi {
            notificationService.registerToken(token.toRequestBody())
        }
        return response.toDomainModel()
    }
}
