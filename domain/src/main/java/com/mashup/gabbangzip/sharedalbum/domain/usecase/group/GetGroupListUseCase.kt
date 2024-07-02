package com.mashup.gabbangzip.sharedalbum.domain.usecase.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetGroupListUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(): Result<List<GroupDomainModel>> {
        return runCatching { groupRepository.getGroupList() }
    }
}
