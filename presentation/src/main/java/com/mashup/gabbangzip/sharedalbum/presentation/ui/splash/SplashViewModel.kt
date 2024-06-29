package com.mashup.gabbangzip.sharedalbum.presentation.ui.splash

import androidx.lifecycle.ViewModel
import com.mashup.gabbangzip.sharedalbum.domain.usecase.CheckUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SplashUiState())
    val state = _state.asStateFlow()

    fun checkUserLoggedIn() {
        _state.update { state ->
            state.copy(isLoading = true)
        }
        val isUserLoggedIn = checkUserLoggedInUseCase()
        _state.update { state ->
            state.copy(
                isUserLoggedIn = isUserLoggedIn,
                isLoading = false,
            )
        }
    }
}
