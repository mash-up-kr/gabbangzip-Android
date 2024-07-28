package com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.invitation.EnterGroupByInvitationCodeUseCase
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
                errorMessage = null,
            )
        }
        viewModelScope.launch {
            enterGroupByInvitationCodeUseCase(code)
                .onSuccess {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            isInvitationSuccessful = true,
                            errorMessage = null,
                        )
                    }
                }
                .onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            isInvitationSuccessful = false,
                            errorMessage = "존재하지 않는 초대코드에요.",
                        )
                    }
                }
        }
    }
}
