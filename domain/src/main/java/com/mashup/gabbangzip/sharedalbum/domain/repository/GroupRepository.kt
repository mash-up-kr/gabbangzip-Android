package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel

interface GroupRepository {
    suspend fun createGroup(groupParam: GroupParam): GroupInfoDomainModel
    suspend fun getGroupList(): List<GroupDomainModel>
    suspend fun getGroupDetail(groupId: Long): GroupDomainModel
}
