package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage

data class MyPageUiState(
    val userName: String = "",
    val dialogState: DialogState = DialogState.None,
    val isLoading: Boolean = false,
) {
    enum class DialogState {
        None, Logout, Withdrawal
    }
}
