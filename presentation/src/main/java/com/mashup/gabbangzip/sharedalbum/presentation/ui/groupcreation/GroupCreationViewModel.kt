package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.CreateGroupUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.model.GroupCreated
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.FileUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupCreationViewModel @Inject constructor(
    private val createGroupUseCase: CreateGroupUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GroupCreationState())
    val uiState: StateFlow<GroupCreationState> = _uiState.asStateFlow()

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

    fun createGroup(context: Context) {
        viewModelScope.launch {
            val currentState = uiState.value
            FileUtil.getFileFromUri(context, currentState.thumbnail)?.let { file ->
                createGroupUseCase(
                    name = currentState.name,
                    keyword = currentState.keyword.name,
                    imageFile = file,
                ).onSuccess {
                    _uiState.emit(
                        uiState.value.copy(
                            groupCreated = GroupCreated(
                                imageUrl = it.imageUrl,
                                invitationUrl = it.invitationUrl,
                            ),
                        ),
                    )
                }.onFailure {
                    _effect.emit(Event.ShowWarningToast(message = "이미지 업로드 실패 ($it)"))
                    Log.d(TAG, "이미지 업로드 실패 ($it)")
                }
            } ?: run {
                _effect.emit(Event.ShowWarningToast(message = "이미지 파일 조회 실패"))
                Log.d(TAG, "이미지 파일 조회 실패")
            }
        }
    }

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _effect.emit(Event.ShowCheckToast(message = message))
        }
    }

    sealed interface Event {
        data class ShowWarningToast(val message: String) : Event
        data class ShowCheckToast(val message: String) : Event
    }

    companion object {
        private const val TAG = "GroupCreationViewModel"
    }
}
