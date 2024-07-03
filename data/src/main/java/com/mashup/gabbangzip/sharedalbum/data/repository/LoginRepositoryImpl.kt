package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.LoginRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.TokenRefreshRequest
import com.mashup.gabbangzip.sharedalbum.data.service.LoginService
import com.mashup.gabbangzip.sharedalbum.domain.datasource.LocalDataSource
import com.mashup.gabbangzip.sharedalbum.domain.model.LoginParam
import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfo
import com.mashup.gabbangzip.sharedalbum.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val localDataSource: LocalDataSource,
) : LoginRepository {
    override suspend fun login(param: LoginParam) {
        val request = LoginRequest(
            idToken = param.idToken,
            provider = param.provider,
            nickname = param.nickname,
            profileImage = param.profileImage,
        )
        val response = callApi { loginService.login(loginRequest = request) }
        saveToken(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
        )
        localDataSource.saveUserInfo(
            UserInfo(userName = response.nickname),
        )
    }

    override fun saveToken(accessToken: String, refreshToken: String) {
        localDataSource.saveToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    override fun removeToken() {
        localDataSource.removeToken()
    }

    override fun isUserLoggedIn(): Boolean {
        return localDataSource.getAccessToken()?.isNotBlank() ?: false
    }

    override suspend fun generateNewAccessToken(refreshToken: String) {
        val request = TokenRefreshRequest(
            refreshToken = refreshToken,
        )
        val response = callApi { loginService.getNewAccessToken(request) }
        saveToken(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
        )
    }
}
