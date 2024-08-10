package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.EnterGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.CreateGroupResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.group.EnterGroupResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.group.GroupDataResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.group.GroupDetailResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.group.GroupMemberResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupService {
    @POST("api/v1/groups")
    suspend fun createGroup(
        @Body createGroupRequest: CreateGroupRequest,
    ): PicResponse<CreateGroupResponse>

    @GET("api/v1/groups")
    suspend fun getGroupList(): PicResponse<GroupDataResponse>

    @GET("api/v1/groups/{id}")
    suspend fun getGroupDetail(
        @Path(value = "id") groupId: Long,
    ): PicResponse<GroupDetailResponse>

    @GET("api/v1/groups/{id}/members")
    suspend fun getGroupMembers(
        @Path(value = "id") groupId: Long,
    ): PicResponse<GroupMemberResponse>

    @GET("api/v1/groups/join")
    suspend fun enterGroupByCode(
        @Body enterGroupRequest: EnterGroupRequest,
    ): PicResponse<EnterGroupResponse>
}
