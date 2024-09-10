package com.mashup.gabbangzip.sharedalbum.domain.usecase.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.WithdrawalGroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class WithdrawGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(groupId: Long): Result<WithdrawalGroupDomainModel> {
        return runCatching {
            groupRepository.withdrawGroup(groupId)
        }
    }
}
