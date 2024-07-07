package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupCreationViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(GroupCreationState())
    val uiState: StateFlow<GroupCreationState> = _uiState.asStateFlow()

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
}
