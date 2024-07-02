package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam

interface GroupRepository {
    suspend fun createGroup(groupParam: GroupParam): GroupInfo
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel

interface GroupRepository {
    suspend fun getGroupList(): List<GroupDomainModel>
}
