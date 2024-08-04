package com.mashup.gabbangzip.sharedalbum.domain.usecase

import dagger.Reusable
import javax.inject.Inject

@Reusable
class PokeOtherMemberUseCase @Inject constructor() {
    suspend operator fun invoke(): Result<Unit> {
        return runCatching {
            // todo
        }
    }
}
