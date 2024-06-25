package com.mashup.gabbangzip.sharedalbum.domain.datasource

import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfo

interface LocalDataSource {
    fun removeAll()
    fun saveToken(accessToken: String, refreshToken: String)
    fun removeToken()
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun saveUserInfo(userInfo: UserInfo)
    fun loadUserInfo(): UserInfo
}
