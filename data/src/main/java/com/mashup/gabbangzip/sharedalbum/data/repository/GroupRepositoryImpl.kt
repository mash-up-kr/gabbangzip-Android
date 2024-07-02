package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.service.GroupService
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupService: GroupService,
) : GroupRepository {
    override suspend fun createGroup(groupParam: GroupParam): GroupInfo {
        val request = CreateGroupRequest(
            groupName = groupParam.groupName,
            keyword = groupParam.keyword,
            groupImageUrl = groupParam.groupImageUrl,
        )
        return groupService.createGroup(request).data?.run {
            GroupInfo(
                id = id,
                groupName = groupName,
                keyword = keyword,
                groupImageUrl = groupImageUrl,
                groupInvitationUrl = groupInvitationUrl,
            )
        } ?: throw IllegalStateException("데이터 없음")
    }
}
