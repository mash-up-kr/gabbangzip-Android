package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.dto.request.CreateGroupRequest
import com.mashup.gabbangzip.sharedalbum.data.service.GroupService
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupDetailInfo
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.model.ImageWithFrame
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupService: GroupService,
) : GroupRepository {
    override suspend fun createGroup(groupParam: GroupParam): GroupInfo {
        val request = CreateGroupRequest(
            groupName = groupParam.name,
            keyword = groupParam.keyword,
            groupImageUrl = groupParam.imageUrl,
        )
        return groupService.createGroup(request).data?.run {
            GroupInfo(
                id = id,
                name = groupName,
                keyword = keyword,
                imageUrl = groupImageUrl,
                invitationUrl = groupInvitationUrl,
            )
        } ?: throw IllegalStateException("데이터 없음")
    }

    override suspend fun getGroupDetail(groupId: Long): GroupDetailInfo {
        return groupService.getGroupDetail(groupId).data?.run {
            GroupDetailInfo(
                name = name,
                keyword = keyword,
                status = status,
                statusDescription = statusDescription,
                updatedImages = updatedImages,
                piced = piced,
                recentEventDate = recentEventDate,
                cardFrontImageUrl = cardFrontImageUrl,
                cardBackImages = cardBackImages.map {
                    ImageWithFrame(it.imageUrl, it.frameUrl)
                },
            )
        } ?: throw IllegalStateException("데이터 없음")
    }
}
