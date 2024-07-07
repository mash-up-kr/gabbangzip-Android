package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.CreateGroupUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupCreationViewModel @Inject constructor(
    private val createGroupUseCase: CreateGroupUseCase,
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

    fun createGroup() {
        viewModelScope.launch {
            val currentState = uiState.value
            // Todo : 이미지 업로드 방법 문의 중
//            createGroupUseCase(
//                param = GroupParam(
//                    name = currentState.name,
//                    keyword = currentState.keyword.name,
//                    imageUrl = currentState.thumbnail.toString(),
//                )
//            )
        }
    }
}
