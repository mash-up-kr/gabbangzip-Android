package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.common.toS3Url
import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.request.EnterGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.group.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.GroupService
import com.mashup.gabbangzip.sharedalbum.domain.base.PicException
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberListDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.WithdrawalGroupDomainModel
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
                imageUrl = groupImageUrl.toS3Url(),
                invitationCode = invitationCode,
            )
        }
    }

    override suspend fun enterGroupByCode(code: String): Long {
        val request = EnterGroupRequest(code)
        return runCatching {
            callApi { groupService.enterGroupByCode(request) }.groupId
        }.getOrElse { e ->
            val message = e.message
            throw when {
                message == null -> e
                message.contains(MESSAGE_GROUP_NOT_FOUND) -> PicException.InvalidGroupCodeException
                message.contains(MESSAGE_GROUP_OVERFLOW) -> PicException.GroupOverflowException
                else -> e
            }
        }
    }

    override suspend fun getGroupList(): List<GroupDomainModel> {
        return callApi { groupService.getGroupList() }.toDomainModel()
    }

    override suspend fun getGroupDetail(groupId: Long): GroupDomainModel {
        return callApi { groupService.getGroupDetail(groupId) }.toDomainModel()
    }

    override suspend fun getGroupMembers(groupId: Long): MemberListDomainModel {
        return callApi { groupService.getGroupMembers(groupId) }.toDomainModel()
    }

    override suspend fun withdrawGroup(groupId: Long): WithdrawalGroupDomainModel {
        return callApi { groupService.withdrawGroup(groupId) }.toDomainModel()
    }

    companion object {
        private const val MESSAGE_GROUP_NOT_FOUND = "해당하는 그룹을 찾을 수 없습니다"
        private const val MESSAGE_GROUP_OVERFLOW = "인원 초과"
    }
}
