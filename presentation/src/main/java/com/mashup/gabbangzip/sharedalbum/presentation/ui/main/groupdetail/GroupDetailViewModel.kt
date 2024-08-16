package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.event.CheckVisitUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupDetailUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupDetailUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGroupDetailUseCase: GetGroupDetailUseCase,
    private val checkVisitUseCase: CheckVisitUseCase,
) : ViewModel() {
    private val groupId = savedStateHandle.get<Long>(MainRoute.GroupDetailRoute.KEY_GROUP_ID)

    private val _uiState = MutableStateFlow(GroupDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        if (groupId != null) {
            getGroupDetail(groupId)
        } else {
            _uiState.update { state ->
                state.copy(isError = true)
            }
        }
    }

    private fun getGroupDetail(groupId: Long) {
        _uiState.update { state ->
            state.copy(isLoading = true)
        }
        viewModelScope.launch {
            getGroupDetailUseCase(groupId)
                .onSuccess { groupDetail ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            groupInfo = groupDetail.toUiModel(),
                            status = GroupStatusType.getType(groupDetail.status),
                            recentEvent = groupDetail.recentEvent.toUiModel(),
                        )
                    }
                }.onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            isError = true,
                        )
                    }
                }
        }
    }

    fun checkVisit() {
        uiState.value.recentEvent?.let { event ->
            viewModelScope.launch {
                checkVisitUseCase(event.id)
            }
        }
    }
}
