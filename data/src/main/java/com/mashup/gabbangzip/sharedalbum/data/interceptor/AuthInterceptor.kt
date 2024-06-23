package com.mashup.gabbangzip.sharedalbum.data.interceptor

import com.mashup.gabbangzip.sharedalbum.domain.datasource.LocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val localDataSource: LocalDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = localDataSource.getAccessToken() ?: return chain.proceed(chain.request())

        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build(),
        )
    }
}
