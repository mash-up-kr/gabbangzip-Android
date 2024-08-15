package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.event.CreateEventUseCase
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
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class EventCreationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createEventUseCase: CreateEventUseCase,
) : ViewModel() {
    private val groupId =
        savedStateHandle.get<Long>(EventCreationActivity.INTENT_EXTRA_GROUP_ID) ?: -1

    private val _uiState = MutableStateFlow(EventCreationState())
    val uiState: StateFlow<EventCreationState> = _uiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<EventCreationEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

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

    private fun clearPictures() {
        viewModelScope.launch {
            _uiState.update { it.copy(pictures = ImmutableList(emptyList())) }
        }
    }

    fun clearEventCreationState() {
        viewModelScope.launch {
            _uiState.update { EventCreationState() }
        }
    }

    fun checkEventCreation(description: String, fileList: List<File>) {
        if (fileList.size == uiState.value.pictures.size) {
            createEvent(description, fileList)
        } else {
            showSnackBar()
            clearPictures()
        }
    }

    private fun createEvent(description: String, fileList: List<File>) {
        viewModelScope.launch {
            createEventUseCase(
                groupId = groupId,
                description = description,
                date = LocalDateTime.now().toString(),
                fileList = fileList,
            ).onSuccess {
                Log.d(TAG, "이벤트 생성 성공")
                // 그룹 상세 화면으로 이동
            }.onFailure {
                Log.d(TAG, "이벤트 생성 실패")
                showSnackBar()
            }
        }
    }

    private fun showSnackBar() {
        viewModelScope.launch { _eventFlow.emit(EventCreationEvent.Error) }
    }

    companion object {
        private const val TAG = "EventCreationViewModel"
    }
}
