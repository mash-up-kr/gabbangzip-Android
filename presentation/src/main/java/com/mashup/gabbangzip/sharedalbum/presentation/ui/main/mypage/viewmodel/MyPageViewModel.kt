package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LoadUserInfoUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LogoutUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.WithdrawalUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.auth.KakaoUserSdkUtil
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val loadUserInfoUseCase: LoadUserInfoUseCase,
    private val withdrawalUseCase: WithdrawalUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    init {
        val userName = loadUserInfoUseCase().name
        _uiState.update {
            it.copy(userName = userName)
        }
    }

    fun dismissDialog() {
        _uiState.update {
            it.copy(dialogState = MyPageUiState.DialogState.None)
        }
    }

    fun showLogoutDialog() {
        _uiState.update {
            it.copy(dialogState = MyPageUiState.DialogState.Logout)
        }
    }

    fun showWithdrawalDialog() {
        _uiState.update {
            it.copy(dialogState = MyPageUiState.DialogState.Withdrawal)
        }
    }

    fun logout() {
        viewModelScope.launch {
            KakaoUserSdkUtil.logout()
            logoutUseCase()
        }
    }

    fun withdrawal(onSuccess: () -> Unit) {
        _uiState.update {
            it.copy(isLoading = true, errorMessage = null)
        }
        KakaoUserSdkUtil.withdrawal(
            onSuccess = {
                viewModelScope.launch {
                    withdrawalUseCase()
                    _uiState.update {
                        it.copy(isLoading = false)
                    }
                    onSuccess()
                }
            },
            onFailure = {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMessage = "카카오 서버 회원 탈퇴 실패 $it",
                    )
                }
            },
        )
    }

    companion object {
        private const val TAG = "MyPageViewModel"
    }
}
