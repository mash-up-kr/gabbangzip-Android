package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.toRequest
import com.mashup.gabbangzip.sharedalbum.data.dto.response.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.FirebaseService
import com.mashup.gabbangzip.sharedalbum.domain.model.firebase.FcmTokenDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.firebase.FcmTokenParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseService: FirebaseService,
) : FirebaseRepository {
    override suspend fun registerToken(token: FcmTokenParamDomainModel): FcmTokenDomainModel {
        val response = callApi {
            firebaseService.registerToken(token.toRequest())
        }
        return response.toDomainModel()
    }
}
