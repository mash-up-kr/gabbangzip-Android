package com.mashup.gabbangzip.sharedalbum.data.di

import com.mashup.gabbangzip.sharedalbum.data.BuildConfig
import com.mashup.gabbangzip.sharedalbum.data.di.qualifier.AuthClient
import com.mashup.gabbangzip.sharedalbum.data.di.qualifier.AuthRetrofit
import com.mashup.gabbangzip.sharedalbum.data.di.qualifier.DefaultClient
import com.mashup.gabbangzip.sharedalbum.data.di.qualifier.DefaultRetrofit
import com.mashup.gabbangzip.sharedalbum.data.interceptor.AuthInterceptor
import com.mashup.gabbangzip.sharedalbum.data.interceptor.TokenAuthenticator
import com.mashup.gabbangzip.sharedalbum.data.service.AwsService
import com.mashup.gabbangzip.sharedalbum.data.service.EventService
import com.mashup.gabbangzip.sharedalbum.data.service.FileService
import com.mashup.gabbangzip.sharedalbum.data.service.GroupService
import com.mashup.gabbangzip.sharedalbum.data.service.LoginService
import com.mashup.gabbangzip.sharedalbum.data.service.NotificationService
import com.mashup.gabbangzip.sharedalbum.data.service.UserService
import com.mashup.gabbangzip.sharedalbum.data.service.VoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @DefaultClient
    @Singleton
    @Provides
    fun provideDefaultOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .build()
    }

    @AuthClient
    @Singleton
    @Provides
    fun provideAuthOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @DefaultRetrofit
    @Singleton
    @Provides
    fun provideDefaultRetrofit(
        @DefaultClient okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @AuthRetrofit
    @Singleton
    @Provides
    fun provideAuthRetrofit(
        @AuthClient okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideLoginService(
        @AuthRetrofit retrofit: Retrofit,
    ): LoginService = retrofit.create()

    @Singleton
    @Provides
    fun provideUserService(
        @DefaultRetrofit retrofit: Retrofit,
    ): UserService = retrofit.create()

    @Singleton
    @Provides
    fun provideGroupService(
        @DefaultRetrofit retrofit: Retrofit,
    ): GroupService = retrofit.create()

    @Singleton
    @Provides
    fun provideFileService(
        @DefaultRetrofit retrofit: Retrofit,
    ): FileService = retrofit.create()

    @Singleton
    @Provides
    fun provideAwsService(
        @AuthRetrofit retrofit: Retrofit,
    ): AwsService = retrofit.create()

    @Singleton
    @Provides
    fun provideNotificationService(
        @DefaultRetrofit retrofit: Retrofit,
    ): NotificationService = retrofit.create()

    @Singleton
    @Provides
    fun provideVoteService(
        @DefaultRetrofit retrofit: Retrofit,
    ): VoteService = retrofit.create()

    @Singleton
    @Provides
    fun provideEventService(
        @DefaultRetrofit retrofit: Retrofit,
    ): EventService = retrofit.create()

    companion object {
        private const val BASE_URL = "http://ec2-43-203-14-157.ap-northeast-2.compute.amazonaws.com"
    }
}
