package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfo

interface UserRepository {
    fun saveUserInfo(userInfo: UserInfo)
    fun loadUserInfo(): UserInfo
    suspend fun deleteUser()
}
