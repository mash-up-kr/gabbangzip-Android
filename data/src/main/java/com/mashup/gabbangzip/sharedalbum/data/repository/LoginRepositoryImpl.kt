package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.dto.request.LoginRequest
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
        runCatching {
            loginService.login(loginRequest = request)
        }.onSuccess { response ->
            response.data?.run {
                saveToken(
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                )
                localDataSource.saveUserInfo(
                    UserInfo(userName = nickname),
                )
            } ?: throw IllegalStateException("데이터 없음")
        }.getOrThrow()
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
}
