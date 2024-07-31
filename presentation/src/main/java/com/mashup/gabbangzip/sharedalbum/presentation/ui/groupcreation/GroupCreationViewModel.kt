package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

import android.net.Uri
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.CreateGroupUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.model.GroupCreationResult
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class GroupCreationViewModel @Inject constructor(
    private val createGroupUseCase: CreateGroupUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GroupCreationUiState())
    val uiState: StateFlow<GroupCreationUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<Event>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val effect = _effect.asSharedFlow()

    fun updateName(name: String) {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(name = name))
        }
    }

    fun updateKeyword(keyword: GroupKeyword) {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(keyword = keyword))
        }
    }

    fun updateThumbnail(thumbnail: Uri?) {
        viewModelScope.launch {
            if (thumbnail != null) {
                _uiState.emit(uiState.value.copy(thumbnail = thumbnail))
            }
        }
    }

    fun createGroup(name: String, keyword: String, file: File) {
        viewModelScope.launch {
            createGroupUseCase(
                name = name,
                keyword = keyword,
                imageFile = file,
            ).onSuccess {
                _uiState.emit(
                    uiState.value.copy(
                        groupCreationResult = GroupCreationResult(
                            name = it.name,
                            keyword = GroupKeyword.getKeyword(it.keyword),
                            imageUrl = it.imageUrl,
                            invitationCode = it.invitationCode,
                        ),
                    ),
                )
            }.onFailure {
                _effect.emit(Event.ShowSnackBarMessageRes(PicSnackbarType.WARNING, message = R.string.image_upload_failed))
            }
        }
    }

    fun showSnackBar(type: PicSnackbarType, message: String) {
        viewModelScope.launch {
            _effect.emit(Event.ShowSnackBarMessage(type = type, message = message))
        }
    }

    fun showSnackBar(type: PicSnackbarType, @StringRes message: Int) {
        viewModelScope.launch {
            _effect.emit(Event.ShowSnackBarMessageRes(type = type, message = message))
        }
    }

    sealed interface Event {
        data class ShowSnackBarMessage(val type: PicSnackbarType, val message: String) : Event
        data class ShowSnackBarMessageRes(val type: PicSnackbarType, @StringRes val message: Int) : Event
    }

    companion object {
        private const val TAG = "GroupCreationViewModel"
    }
}
