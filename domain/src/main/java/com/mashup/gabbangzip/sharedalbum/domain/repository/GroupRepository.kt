package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberListDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.WithdrawalGroupDomainModel

interface GroupRepository {
    suspend fun createGroup(groupParam: GroupParam): GroupInfoDomainModel
    suspend fun enterGroupByCode(code: String): Long
    suspend fun getGroupList(): List<GroupDomainModel>
    suspend fun getGroupDetail(groupId: Long): GroupDomainModel
    suspend fun getGroupMembers(groupId: Long): MemberListDomainModel
    suspend fun withdrawGroup(groupId: Long): WithdrawalGroupDomainModel
}
