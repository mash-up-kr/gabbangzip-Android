package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.LoginParam

interface LoginRepository {
    suspend fun login(param: LoginParam)
    fun saveToken(accessToken: String, refreshToken: String)
    fun removeToken()
    fun isUserLoggedIn(): Boolean
}
