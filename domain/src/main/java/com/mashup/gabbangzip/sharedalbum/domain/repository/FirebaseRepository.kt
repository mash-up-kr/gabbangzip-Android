package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.firebase.FcmTokenDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.firebase.FcmTokenParamDomainModel

interface FirebaseRepository {
    suspend fun registerToken(token: FcmTokenParamDomainModel): FcmTokenDomainModel
}
