package com.mashup.gabbangzip.sharedalbum.domain.usecase.invitation

import dagger.Reusable
import javax.inject.Inject

@Reusable
class EnterGroupByInvitationCodeUseCase @Inject constructor() {
    suspend operator fun invoke(code: String): Result<Unit> {
        return runCatching {
            // todo
        }
    }
}
