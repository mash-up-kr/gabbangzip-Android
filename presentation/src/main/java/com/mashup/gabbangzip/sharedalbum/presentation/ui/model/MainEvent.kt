package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

sealed interface MainEvent {
    data object Empty : MainEvent
    data object Success : MainEvent
    data object Loading : MainEvent
    data object Error : MainEvent
}
