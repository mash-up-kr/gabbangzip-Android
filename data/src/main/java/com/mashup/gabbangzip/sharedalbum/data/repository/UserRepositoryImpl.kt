package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.service.UserService
import com.mashup.gabbangzip.sharedalbum.domain.datasource.LocalDataSource
import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val localDataSource: LocalDataSource,
) : UserRepository {
    override fun loadUserInfo(): UserInfoDomainModel {
        return localDataSource.loadUserInfo()
    }

    override suspend fun deleteUser() {
        userService.deleteUser()
        localDataSource.removeUserInfo()
    }
}
