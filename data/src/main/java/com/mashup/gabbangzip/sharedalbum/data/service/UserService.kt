package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.UserDeleteResponse
import retrofit2.http.DELETE

interface UserService {
    @DELETE("api/v1/user")
    suspend fun deleteUser(): PicResponse<UserDeleteResponse>
}
