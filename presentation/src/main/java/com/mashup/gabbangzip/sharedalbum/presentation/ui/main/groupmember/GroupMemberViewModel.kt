package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.base.PicException
import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupMembersUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.WithdrawGroupUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private val _event: MutableSharedFlow<GroupMemberEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        if (groupId != null) {
            getGroupMembers(groupId)
        }
    }

    private fun getGroupMembers(groupId: Long) {
        updateLoadingState(isLoading = true)
        viewModelScope.launch {
            getGroupMembersUseCase(groupId)
                .onSuccess { response ->
                    updateLoadingState(isLoading = false)
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
                }.onFailure { e ->
                    updateLoadingState(isLoading = false)
                    updateEvent(GroupMemberEvent.FailureWithdrawGroup(mapErrorMessageRes(e)))
                }
        }
    }

    fun withdrawGroup(onSuccess: () -> Unit) {
        updateLoadingState(isLoading = true)
        groupId?.let {
            viewModelScope.launch {
                withdrawGroupUseCase(groupId)
                    .onSuccess {
                        updateLoadingState(isLoading = false)
                        onSuccess()
                    }.onFailure { e ->
                        updateLoadingState(isLoading = false)
                        updateEvent(GroupMemberEvent.FailureWithdrawGroup(mapErrorMessageRes(e)))
                    }
            }
        } ?: {
            updateLoadingState(isLoading = false)
            updateEvent(GroupMemberEvent.FailureWithdrawGroup(R.string.error_retry))
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }

    private fun updateEvent(event: GroupMemberEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    private fun mapErrorMessageRes(e: Throwable): Int {
        return when (e) {
            is PicException.UnknownException -> R.string.error_network
            is PicException.NoWithdrawGroupException -> R.string.group_withdraw_failure
            else -> R.string.error_server
        }
    }
}
