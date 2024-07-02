package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.CreateGroupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GroupService {
    @POST("api/v1/groups")
    suspend fun createGroup(
        @Body loginRequest: CreateGroupRequest,
    ): PicResponse<CreateGroupResponse>
}
