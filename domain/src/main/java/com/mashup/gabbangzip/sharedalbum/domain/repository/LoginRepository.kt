package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.usecase.LoginParam

interface LoginRepository {
    suspend fun login(param: LoginParam)
}
