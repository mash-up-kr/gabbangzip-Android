package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import com.mashup.gabbangzip.sharedalbum.domain.model.notification.KookNotificationDomainModel

data class KookNotification(
    val eventId: Long,
)

fun KookNotificationDomainModel.toUiModel(): KookNotification = KookNotification(
    eventId = eventId,
)
