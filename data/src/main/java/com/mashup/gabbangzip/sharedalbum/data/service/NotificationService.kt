package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.notification.FcmTokenRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.notification.KookNotificationRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.notification.FcmTokenResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.notification.KookNotificationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationService {
    @POST("api/v1/alarm/token")
    suspend fun registerToken(
        @Body token: FcmTokenRequest,
    ): PicResponse<FcmTokenResponse>

    @POST("api/v1/alarm/kook")
    suspend fun sendKookNotification(
        @Body eventId: KookNotificationRequest,
    ): PicResponse<KookNotificationResponse>
}
