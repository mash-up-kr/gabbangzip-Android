package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.LocalDateUtil

data class EventCreationState(
    val date: String = LocalDateUtil.getNowDate(),
    val pictures: ImmutableList<Uri?> = ImmutableList(emptyList()),
    val eventCreationSuccess: Long? = null,
    val isLoading: Boolean = false,
)

sealed interface EventCreationEvent {
    data object Error : EventCreationEvent
}
