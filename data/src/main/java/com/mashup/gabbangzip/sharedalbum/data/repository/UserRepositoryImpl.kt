package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.domain.datasource.LocalDataSource
import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfo
import com.mashup.gabbangzip.sharedalbum.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : UserRepository {
    override fun saveUserInfo(userInfo: UserInfo) {
        localDataSource.saveUserInfo(userInfo)
    }

    override fun loadUserInfo(): UserInfo {
        return localDataSource.loadUserInfo()
    }
}
