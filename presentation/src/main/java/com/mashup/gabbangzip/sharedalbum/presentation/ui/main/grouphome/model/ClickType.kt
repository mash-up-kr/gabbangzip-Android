package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

sealed interface ClickType {
    data object Fcm : ClickType
    data object Gallery : ClickType
    data object Vote : ClickType
}
