package com.mashup.gabbangzip.sharedalbum.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _effect: MutableSharedFlow<Event> = MutableSharedFlow(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val effect: SharedFlow<Event> = _effect.asSharedFlow()

    fun showToastMessage(toastMessage: String, snackbarType: PicSnackbarType) {
        viewModelScope.launch {
            _effect.emit(
                Event.ShowToast(
                    message = toastMessage,
                    snackbarType = snackbarType,
                ),
            )
        }
    }

    sealed interface Event {
        class ShowToast(val message: String, val snackbarType: PicSnackbarType) : Event
    }
}
