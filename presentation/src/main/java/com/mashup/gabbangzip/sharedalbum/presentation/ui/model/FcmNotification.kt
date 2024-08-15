package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmNotificationDomainModel

data class FcmNotification(
    val eventId: Long,
)

fun FcmNotificationDomainModel.toUiModel(): FcmNotification = FcmNotification(
    eventId = eventId,
)
