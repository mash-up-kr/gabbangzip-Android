package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.PokeOtherMemberUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.UploadMyPicUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupDetailUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupDetailUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
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
    private val uploadMyPicUseCase: UploadMyPicUseCase,
    private val pokeOtherMemberUseCase: PokeOtherMemberUseCase,
) : ViewModel() {
    private val groupId = savedStateHandle.get<Long>(MainRoute.GroupDetailRoute.KEY_GROUP_ID)

    private val _uiState = MutableStateFlow(GroupDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        if (groupId != null) {
            getGroupDetail(groupId)
        } else {
            _uiState.update { state ->
                state.copy(error = "잘못된 접근입니다.")
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
                            recentEvent = groupDetail.recentEvent.toUiModel(),
                            error = null,
                        )
                    }
                }.onFailure { e ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = e.message,
                        )
                    }
                }
        }
    }

    fun pokeOtherUser() {
        viewModelScope.launch {
            pokeOtherMemberUseCase()
                .onSuccess {

                }.onFailure {

                }
        }
    }
}
