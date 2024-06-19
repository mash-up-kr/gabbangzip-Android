package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.LoginParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(param: LoginParam): Result<Unit> {
        return runCatching { loginRepository.login(param) }
    }
}
