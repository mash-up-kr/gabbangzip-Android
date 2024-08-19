package com.mashup.gabbangzip.sharedalbum.domain.usecase.invitation

import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class EnterGroupByInvitationCodeUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(code: String): Result<Long> {
        return runCatching {
            groupRepository.enterGroupByCode(code)
        }
    }
}
