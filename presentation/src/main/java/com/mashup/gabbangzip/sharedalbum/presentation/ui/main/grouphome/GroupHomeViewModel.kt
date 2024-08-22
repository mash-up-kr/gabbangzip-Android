package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupListUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupHomeUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class GroupHomeViewModel @Inject constructor(
    getGroupListUseCase: GetGroupListUseCase,
) : ViewModel() {

    val groupUiState = getGroupListUseCase()
        .map { groupList ->
            if (groupList.isEmpty()) {
                GroupHomeUiState.NoGroup
            } else {
                GroupHomeUiState.GroupList(ImmutableList(groupList.toUiModel()))
            }
        }
        .catch {
            emit(
                GroupHomeUiState.Error(
                    when (it) {
                        is UnknownHostException -> R.string.error_network
                        else -> R.string.error_server
                    },
                ),
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = GroupHomeUiState.NotInitialized,
        )
}
