package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.KookNotificationDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.KookNotificationParamDomainModel

interface NotificationRepository {
    suspend fun registerFcmToken(token: FcmTokenParamDomainModel): FcmTokenDomainModel
    suspend fun sendKookNotification(kookParam: KookNotificationParamDomainModel): KookNotificationDomainModel
}
