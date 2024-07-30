package com.mashup.gabbangzip.sharedalbum.domain.usecase.firebase

import com.mashup.gabbangzip.sharedalbum.domain.model.firebase.FcmTokenDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.firebase.FcmTokenParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.FirebaseRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RegisterFcmTokenUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) {
    suspend operator fun invoke(token: String): Result<FcmTokenDomainModel> {
        return runCatching {
            firebaseRepository.registerToken(FcmTokenParamDomainModel(token))
        }
    }
}
