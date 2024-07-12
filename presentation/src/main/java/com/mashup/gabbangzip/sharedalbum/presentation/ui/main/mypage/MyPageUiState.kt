package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage

data class MyPageUiState(
    val userName: String = "",
    val dialogState: DialogState = DialogState.None,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    enum class DialogState {
        None, Logout, Withdrawal
    }
}
