package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.model

sealed interface MainEvent {
    data object SuccessNotification : MainEvent
    data object FailNotification : MainEvent
    data object SuccessUploadMyPic : MainEvent
    data object FailUploadMyPic : MainEvent
}
