package com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.base.PicException
import com.mashup.gabbangzip.sharedalbum.domain.usecase.invitation.EnterGroupByInvitationCodeUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvitationCodeViewModel @Inject constructor(
    private val enterGroupByInvitationCodeUseCase: EnterGroupByInvitationCodeUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(InvitationCodeUiState())
    val uiState = _uiState.asStateFlow()

    fun enterGroupByCode(code: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = true,
                errorMessageRes = null,
            )
        }
        viewModelScope.launch {
            enterGroupByInvitationCodeUseCase(code)
                .onSuccess {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            isInvitationSuccessful = true,
                        )
                    }
                }
                .onFailure { e ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            isInvitationSuccessful = false,
                            errorMessageRes = mapErrorMessageRes(e),
                        )
                    }
                }
        }
    }

    private fun mapErrorMessageRes(e: Throwable): Int {
        return when (e) {
            is PicException.InvalidGroupCodeException -> {
                R.string.enter_group_by_code_failure_not_found
            }

            is PicException.GroupOverflowException -> {
                R.string.enter_group_by_code_failure_overflow
            }

            else -> R.string.error_network
        }
    }
}
