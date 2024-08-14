package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

sealed interface MainEvent {
    data object SuccessNotification : MainEvent
    data object FailNotification : MainEvent
}
