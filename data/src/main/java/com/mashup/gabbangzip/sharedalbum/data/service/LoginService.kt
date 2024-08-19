package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.LoginRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.TokenRefreshRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.LoginResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.TokenRefreshResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/v1/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): PicResponse<LoginResponse>

    @POST("/api/v1/auth/token")
    suspend fun getNewAccessToken(
        @Body request: TokenRefreshRequest,
    ): PicResponse<TokenRefreshResponse>
}
