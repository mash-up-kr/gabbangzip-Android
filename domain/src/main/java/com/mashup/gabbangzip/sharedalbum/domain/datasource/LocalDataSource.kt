package com.mashup.gabbangzip.sharedalbum.domain.datasource

interface LocalDataSource {
    fun saveToken(accessToken: String, refreshToken: String)
    fun removeToken()
    fun removeAll()
}
