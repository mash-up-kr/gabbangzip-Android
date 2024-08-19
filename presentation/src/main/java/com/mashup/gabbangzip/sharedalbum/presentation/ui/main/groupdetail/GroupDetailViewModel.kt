package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.event.MarkEventVisitUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupDetailUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupDetailUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getGroupDetailUseCase: GetGroupDetailUseCase,
    private val markEventVisitUseCase: MarkEventVisitUseCase,
) : ViewModel() {
    private val groupId = savedStateHandle.get<Long>(MainRoute.GroupDetailRoute.KEY_GROUP_ID) ?: 0

    val uiState = getGroupDetailUseCase(groupId)
        .map { groupDetail ->
            GroupDetailUiState(
                isLoading = false,
                groupInfo = groupDetail.toUiModel(),
                status = GroupStatusType.getType(groupDetail.status),
                recentEvent = groupDetail.recentEvent.toUiModel(),
                history = groupDetail.history.map { it.toUiModel() },
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = GroupDetailUiState(),
        )

    fun markEventVisit() {
        uiState.value.recentEvent?.let { event ->
            viewModelScope.launch {
                markEventVisitUseCase(event.id)
            }
        }
    }
}
