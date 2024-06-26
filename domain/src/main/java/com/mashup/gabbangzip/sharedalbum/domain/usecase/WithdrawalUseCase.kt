package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.repository.UserRepository
import javax.inject.Inject

class WithdrawalUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() {
        userRepository.deleteUser()
    }
}
