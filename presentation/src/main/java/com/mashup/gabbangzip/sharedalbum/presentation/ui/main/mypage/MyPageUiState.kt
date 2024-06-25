package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage

import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MyPageUiState(
    val userName: String = "",
    val dialogState: DialogState = DialogState.None,
) {
    enum class DialogState {
        None, Logout, Withdrawal
    }
}
