package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(param: GroupParam): Result<GroupInfoDomainModel> {
        return runCatching { groupRepository.createGroup(param) }
    }
}
