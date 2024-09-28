package com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation

import androidx.annotation.StringRes

data class InvitationCodeUiState(
    val isLoading: Boolean = false,
    val isInvitationSuccessful: Boolean? = null,
    @StringRes val errorMessageRes: Int? = null,
)
