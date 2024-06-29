package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.repository.LoginRepository
import javax.inject.Inject

class CheckUserLoggedInUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(): Boolean {
        return loginRepository.isUserLoggedIn()
    }
}
