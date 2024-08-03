package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupInfoDomainModel

interface GroupRepository {
    suspend fun createGroup(groupParam: GroupParam): GroupInfoDomainModel
    suspend fun enterGroupByCode(code: String): Long
    suspend fun getGroupList(): List<GroupDomainModel>
    suspend fun getGroupDetail(groupId: Long): GroupDomainModel
}
