package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class EventCreationState(
    val date: Long? = null,
    val pictures: ImmutableList<Uri?> = ImmutableList(emptyList()),
    val eventCreationSuccess: Long? = null,
    val isLoading: Boolean = false,
)

sealed interface EventCreationEvent {
    data object Error : EventCreationEvent
    data object OverflowImageError : EventCreationEvent
}
