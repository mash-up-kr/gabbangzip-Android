package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.FcmTokenRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.FcmTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationService {
    @POST("api/v1/alarm/token")
    suspend fun registerToken(
        @Body token: FcmTokenRequest,
    ): PicResponse<FcmTokenResponse>
}
