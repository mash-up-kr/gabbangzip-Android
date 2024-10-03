package com.mashup.gabbangzip.sharedalbum.domain.usecase.config

import com.mashup.gabbangzip.sharedalbum.domain.repository.ConfigRepository
import javax.inject.Inject

class SaveIsFirstOpenUseCase @Inject constructor(
    private val configRepository: ConfigRepository,
) {
    suspend operator fun invoke(isFirstOpen: Boolean) {
        configRepository.saveIsFirstOpen(isFirstOpen)
    }
}
