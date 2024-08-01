package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.model

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmTokenDomainModel

data class FcmToken(
    val fcmRegisteredToken: String,
)

fun FcmTokenDomainModel.toUiModel(): FcmToken = FcmToken(
    fcmRegisteredToken = fcmRegisteredToken,
)
