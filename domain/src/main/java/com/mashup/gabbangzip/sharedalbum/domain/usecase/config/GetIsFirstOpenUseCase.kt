package com.mashup.gabbangzip.sharedalbum.domain.usecase.config

import com.mashup.gabbangzip.sharedalbum.domain.repository.ConfigRepository
import javax.inject.Inject

class GetIsFirstOpenUseCase @Inject constructor(
    private val configRepository: ConfigRepository,
) {
    suspend operator fun invoke(): Result<Boolean> {
        return kotlin.runCatching {
            configRepository.getIsFirstOpen()
        }
    }
}
