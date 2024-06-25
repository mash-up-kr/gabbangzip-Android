package com.mashup.gabbangzip.sharedalbum.presentation.ui.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isUserLoggedIn: Boolean = false,
)
