package com.mashup.gabbangzip.sharedalbum.domain.repository

interface ConfigRepository {
    suspend fun saveIsFirstOpen(isFirstOpen: Boolean)
    suspend fun getIsFirstOpen(): Boolean
}
