package com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation

data class InvitationCodeUiState(
    val isLoading: Boolean = false,
    val isInvitationSuccessful: Boolean = false,
    val errorMessage: String? = null,
)
