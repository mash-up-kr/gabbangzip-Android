package com.mashup.gabbangzip.sharedalbum.domain.usecase.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupDetailUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(groupId: Long): Result<GroupDomainModel> {
        return runCatching { groupRepository.getGroupDetail(groupId) }
    }
}
