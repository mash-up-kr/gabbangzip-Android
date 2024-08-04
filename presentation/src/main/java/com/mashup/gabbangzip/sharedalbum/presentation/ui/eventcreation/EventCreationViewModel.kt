package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.CreateEventUseCase
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
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EventCreationViewModel @Inject constructor(
    private val createEventUseCase: CreateEventUseCase,
) : ViewModel() {
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
            createEventUseCase(
                summary = summary,
                date = LocalDate.now().toString(), // ??
                fileList = fileList,
            ).onSuccess {
                Log.d(TAG, "이벤트 생성 성공")
            }.onFailure {
                Log.d(TAG, "이벤트 생성 실패")
                showSnackBar() // 이벤트 생성 실패 스낵바여야 함
                // clear 해야하나?
            }
        }
    }

    fun showSnackBar() {
        viewModelScope.launch { _eventFlow.emit(EventCreationEvent.Error) }
    }

    companion object {
        private const val TAG = "EventCreationViewModel"
    }
}
