package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.domain.datasource.LocalDataSource
import com.mashup.gabbangzip.sharedalbum.domain.repository.ConfigRepository
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : ConfigRepository {
    override suspend fun saveIsFirstOpen(isFirstOpen: Boolean) {
        localDataSource.saveIsFirstOpen(isFirstOpen)
    }

    override suspend fun getIsFirstOpen(): Boolean {
        return localDataSource.getIsFirstOpen()
    }

    override suspend fun saveHomeAlignState(alignState: String) {
        localDataSource.saveHomeAlignState(alignState)
    }

    override suspend fun getHomeAlignState(): String {
        return localDataSource.getHomeAlignState()
    }
}
