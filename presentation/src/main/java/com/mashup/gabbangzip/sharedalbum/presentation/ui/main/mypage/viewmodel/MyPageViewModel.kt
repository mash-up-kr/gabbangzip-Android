package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LoadUserInfoUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LogoutUseCase
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
    private val loadUserInfoUseCase: LoadUserInfoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    init {
        val userName = loadUserInfoUseCase().userName
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
            logoutUseCase()
        }
    }

    fun withdrawal() {
        // Todo : 회원탈퇴 로직 작성하기
    }
}
