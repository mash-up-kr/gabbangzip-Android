package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.CreateGroupResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.GroupDetailResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupService {
    @POST("api/v1/groups")
    suspend fun createGroup(
        @Body loginRequest: CreateGroupRequest,
    ): PicResponse<CreateGroupResponse>

    @GET("api/v1/groups/{id}")
    suspend fun getGroupDetail(
        @Path(value = "id") groupId: Long,
    ): PicResponse<GroupDetailResponse>
}d
