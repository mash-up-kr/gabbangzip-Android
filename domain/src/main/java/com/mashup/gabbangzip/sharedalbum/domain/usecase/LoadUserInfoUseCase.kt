package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.UserRepository
import javax.inject.Inject

class LoadUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): UserInfoDomainModel {
        return userRepository.loadUserInfo()
    }
}
