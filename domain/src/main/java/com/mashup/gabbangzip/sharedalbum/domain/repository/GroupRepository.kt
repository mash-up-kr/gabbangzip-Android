package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.GroupDetailInfo
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam

interface GroupRepository {
    suspend fun createGroup(groupParam: GroupParam): GroupInfo
    suspend fun getGroupDetail(groupId: Long): GroupDetailInfo
}
