package com.mashup.gabbangzip.sharedalbum.domain.datasource

interface LocalDataSource {
    fun removeAll()
    fun saveToken(accessToken: String, refreshToken: String)
    fun removeToken()
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
}
