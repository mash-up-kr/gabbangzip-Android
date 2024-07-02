package com.mashup.gabbangzip.sharedalbum.data.interceptor

import com.mashup.gabbangzip.sharedalbum.domain.base.PicException.LoginRequiredException
import com.mashup.gabbangzip.sharedalbum.domain.datasource.LocalDataSource
import com.mashup.gabbangzip.sharedalbum.domain.repository.LoginRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val loginRepository: LoginRepository,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401) {
            val refreshToken = localDataSource.getRefreshToken() ?: return null
            localDataSource.removeToken()
            val newToken = generateNewAccessToken(refreshToken) ?: return null
            return response.request.newBuilder().apply {
                removeHeader("Authorization")
                addHeader("Authorization", "Bearer $newToken")
            }.build()
        }
        return null
    }

    private fun generateNewAccessToken(refreshToken: String): String? {
        return runCatching {
            runBlocking {
                loginRepository.generateNewAccessToken(refreshToken)
                localDataSource.getAccessToken()
            }
        }.onFailure {
            throw LoginRequiredException
        }.getOrThrow()
    }
}
