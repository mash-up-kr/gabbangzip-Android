package com.mashup.gabbangzip.sharedalbum.data.di

import com.mashup.gabbangzip.sharedalbum.data.repository.EventRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.data.repository.FileRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.data.repository.GroupRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.data.repository.LoginRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.data.repository.NotificationRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.data.repository.UserRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.domain.repository.EventRepository
import com.mashup.gabbangzip.sharedalbum.data.repository.VoteRepositoryImpl
import com.mashup.gabbangzip.sharedalbum.domain.repository.FileRepository
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import com.mashup.gabbangzip.sharedalbum.domain.repository.LoginRepository
import com.mashup.gabbangzip.sharedalbum.domain.repository.NotificationRepository
import com.mashup.gabbangzip.sharedalbum.domain.repository.UserRepository
import com.mashup.gabbangzip.sharedalbum.domain.repository.VoteRepository
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

    @Singleton
    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    fun bindGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository

    @Singleton
    @Binds
    fun bindFileRepository(fileRepositoryImpl: FileRepositoryImpl): FileRepository

    @Singleton
    @Binds
    fun bindNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository

    @Singleton
    @Binds
    fun bindVoteRepository(voteRepositoryImpl: VoteRepositoryImpl): VoteRepository

    @Singleton
    @Binds
    fun bindEventRepository(eventRepository: EventRepositoryImpl): EventRepository
}
