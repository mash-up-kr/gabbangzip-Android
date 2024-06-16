package com.mashup.gabbangzip.sharedalbum.data.di

import com.mashup.gabbangzip.sharedalbum.data.repository.LoginRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
