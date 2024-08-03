package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EventCreationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(EventCreationState())
    val uiState: StateFlow<EventCreationState> = _uiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<EventCreationEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    private val fileList = mutableListOf<File>()

    fun updatePictures(uriList: List<Uri?>) {
        viewModelScope.launch {
            _uiState.update { it.copy(pictures = ImmutableList(uriList.filterNotNull())) }
        }
    }

    fun deletePicture(idx: Int) {
        viewModelScope.launch {
            val pictureList = _uiState.value.pictures.toMutableList()
            pictureList.removeAt(idx)
            _uiState.update { it.copy(pictures = ImmutableList(pictureList)) }
        }
    }

    fun clearPictures() {
        viewModelScope.launch {
            _uiState.update { it.copy(pictures = ImmutableList(emptyList())) }
            fileList.clear()
        }
    }

    fun clearEventCreationState() {
        viewModelScope.launch {
            _uiState.update { EventCreationState() }
        }
    }

    fun addPictureFile(file: File?) {
        file?.let { fileList.add(it) }
    }

    fun checkValidState(): Boolean {
        return fileList.size == uiState.value.pictures.size
    }

    fun createEvent(summary: String) {
        viewModelScope.launch {
            // TODO: create event api 연결
        }
    }

    fun showSnackBar() {
        viewModelScope.launch { _eventFlow.emit(EventCreationEvent.Error) }
    }
}
