package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfo

interface UserRepository {
    fun loadUserInfo(): UserInfo
    suspend fun deleteUser()
}
