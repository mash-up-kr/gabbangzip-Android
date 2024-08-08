package com.mashup.gabbangzip.sharedalbum.domain.usecase.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberListDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetGroupMembersUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(groupId: Long): Result<MemberListDomainModel> {
        return runCatching {
            groupRepository.getGroupMembers(groupId)
        }
    }
}
