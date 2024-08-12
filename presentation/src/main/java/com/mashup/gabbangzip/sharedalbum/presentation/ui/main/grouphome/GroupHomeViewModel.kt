package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupListUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupHomeUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupHomeViewModel @Inject constructor(
    private val getGroupListUseCase: GetGroupListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GroupHomeUiState>(GroupHomeUiState.NotInitialized)
    val uiState = _uiState.asStateFlow()

    init {
        getGroupList()
    }

    private fun getGroupList() {
        _uiState.update { GroupHomeUiState.Loading }
        viewModelScope.launch {
            getGroupListUseCase()
                .onSuccess { response ->
                    _uiState.update {
                        if (response.isEmpty()) {
                            GroupHomeUiState.NoGroup
                        } else {
                            GroupHomeUiState.GroupList(
                                groupList = ImmutableList(response.toUiModel()),
                            )
                        }
                    }
                }
        }
    }
}
