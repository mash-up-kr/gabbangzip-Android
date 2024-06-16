package com.mashup.gabbangzip.sharedalbum.data.repository

import android.util.Log
import com.mashup.gabbangzip.sharedalbum.data.dto.request.LoginRequest
import com.mashup.gabbangzip.sharedalbum.data.service.LoginService
import com.mashup.gabbangzip.sharedalbum.domain.repository.LoginRepository
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LoginParam
import javax.inject.Inject

private const val TAG = "LoginRepositoryImpl"

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
) : LoginRepository {
    override suspend fun login(param: LoginParam) {
        val request = LoginRequest(
            idToken = param.idToken,
            provider = param.provider,
            nickname = param.nickname,
            profileImage = param.profileImage,
        )
        loginService.login(loginRequest = request).also {
            Log.d(TAG, "login: $it")
        }
    }
}
