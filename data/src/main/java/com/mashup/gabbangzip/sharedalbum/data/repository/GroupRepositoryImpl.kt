package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.common.Constants
import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.group.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.GroupService
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupService: GroupService,
) : GroupRepository {
    override suspend fun createGroup(groupParam: GroupParam): GroupInfoDomainModel {
        val request = CreateGroupRequest(
            groupName = groupParam.name,
            keyword = groupParam.keyword,
            groupImageUrl = groupParam.imageUrl,
        )
        val response = callApi { groupService.createGroup(request) }
        return response.run {
            GroupInfoDomainModel(
                id = id,
                name = groupName,
                keyword = keyword,
                imageUrl = "${Constants.S3_BUCKET_DOMAIN_URl}$groupImageUrl",
                invitationUrl = groupInvitationUrl ?: "", // Todo : 초대코드 대응하기
            )
        }
    }

    override suspend fun getGroupList(): List<GroupDomainModel> {
        return callApi { groupService.getGroupList() }.toDomainModel()
    }

    override suspend fun getGroupDetail(groupId: Long): GroupDomainModel {
        return callApi { groupService.getGroupDetail(groupId) }.toDomainModel()
    }
}
