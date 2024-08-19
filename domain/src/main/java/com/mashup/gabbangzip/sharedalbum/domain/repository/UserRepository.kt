package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfoDomainModel

interface UserRepository {
    fun loadUserInfo(): UserInfoDomainModel
    suspend fun deleteUser()
}
