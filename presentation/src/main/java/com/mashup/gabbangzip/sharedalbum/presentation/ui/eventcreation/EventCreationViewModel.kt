package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.event.CreateEventUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.LocalDateUtil
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

    fun updateDate(date: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(date = date) }
        }
    }

    fun updatePictures(uriList: List<Uri?>) {
        viewModelScope.launch {
            val currentList = uiState.value.pictures
            val uriListNotNull = uriList.filterNotNull()
            val addedList =
                if (currentList.size + uriListNotNull.size > EventCreationActivity.PICTURES_MAX_COUNT) {
                    _eventFlow.emit(EventCreationEvent.OverflowImageError)
                    uriListNotNull.take(EventCreationActivity.PICTURES_MAX_COUNT - currentList.size)
                } else {
                    uriListNotNull
                }
            _uiState.update { it.copy(pictures = ImmutableList(currentList + addedList)) }
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
            showErrorSnackBar()
            clearPictures()
        }
    }

    private fun createEvent(description: String, fileList: List<File>) {
        uiState.value.date?.let { date ->
            viewModelScope.launch {
                createEventUseCase(
                    groupId = groupId,
                    description = description,
                    date = LocalDateUtil.parseLongToLocalDateTime(date).toString(),
                    fileList = fileList,
                ).onSuccess {
                    _uiState.update { it.copy(eventCreationSuccess = groupId) }
                    updateLoadingState(isLoading = false)
                }.onFailure {
                    showErrorSnackBar()
                    updateLoadingState(isLoading = false)
                }
            }
        } ?: {
            showErrorSnackBar()
            updateLoadingState(isLoading = false)
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun showErrorSnackBar() {
        viewModelScope.launch { _eventFlow.emit(EventCreationEvent.Error) }
    }

    companion object {
        private const val TAG = "EventCreationViewModel"
    }
}
