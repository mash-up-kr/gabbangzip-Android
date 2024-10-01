package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupMembersUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.WithdrawGroupUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class GroupMemberViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGroupMembersUseCase: GetGroupMembersUseCase,
    private val withdrawGroupUseCase: WithdrawGroupUseCase,
) : ViewModel() {
    private val groupId = savedStateHandle.get<Long>(MainRoute.GroupDetailRoute.KEY_GROUP_ID)
    private val keyword =
        savedStateHandle.get<String>(MainRoute.GroupDetailRoute.KEY_GROUP_KEYWORD).orEmpty()

    private val _state = MutableStateFlow(
        GroupMemberUiState(
            keyWord = GroupKeyword.getKeyword(keyword),
        ),
    )
    val state = _state.asStateFlow()

    init {
        if (groupId != null) {
            getGroupMembers(groupId)
        }
    }

    private fun getGroupMembers(groupId: Long) {
        viewModelScope.launch {
            getGroupMembersUseCase(groupId)
                .onSuccess { response ->
                    _state.update { state ->
                        state.copy(
                            members = ImmutableList(
                                response
                                    .members
                                    .map(MemberDomainModel::toUiModel),
                            ),
                            invitationCode = response.invitationCode,
                        )
                    }
                }.onFailure {
                    _state.update { state ->
                        state.copy(
                            errorMessage = when (it) {
                                is UnknownHostException -> R.string.error_network
                                else -> R.string.error_server
                            },
                        )
                    }
                }
        }
    }

    fun withdrawGroup(onSuccess: () -> Unit) {
        updateLoadingState(isLoading = true)
        viewModelScope.launch {
            if (groupId != null) {
                withdrawGroupUseCase(groupId)
                    .onSuccess {
                        updateLoadingState(isLoading = false)
                        onSuccess()
                    }
                    .onFailure {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = R.string.error_retry,
                            )
                        }
                    }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = R.string.error_retry,
                    )
                }
            }
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
}
