package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.presentation.utils.LocalDateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventCreationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(EventCreationState())
    val uiState: StateFlow<EventCreationState> = _uiState.asStateFlow()

    fun updatePictures(uriList: List<Uri?>) {
        viewModelScope.launch {
            _uiState.update { it.copy(pictures = uriList) }
        }
    }

    fun createEvent() {
        viewModelScope.launch {
            // TODO: create event api 연결
        }
    }
}

data class EventCreationState(
    val summary: String = "",
    val date: String = LocalDateUtil.getNowDate(),
    val pictures: List<Uri?> = emptyList(),
)
