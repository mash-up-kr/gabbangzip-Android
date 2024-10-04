package com.mashup.gabbangzip.sharedalbum.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.CheckUserLoggedInUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.config.GetIsFirstOpenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkUserLoggedInUseCase: CheckUserLoggedInUseCase,
    private val isFirstOpenUseCase: GetIsFirstOpenUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SplashUiState())
    val state = _state.asStateFlow()

    init {
        updateIsFirstOpen()
    }

    private fun updateIsFirstOpen() {
        viewModelScope.launch {
            isFirstOpenUseCase()
                .onSuccess {
                    _state.update { state -> state.copy(isFirstOpen = it) }
                }.onFailure {
                    _state.update { state -> state.copy(isFirstOpen = false) }
                }
        }
    }

    fun checkUserLoggedIn() {
        _state.update { state ->
            state.copy(isLoading = true)
        }
        viewModelScope.launch {
            val isUserLoggedIn = checkUserLoggedInUseCase()
            delay(SPLASH_DISPLAY_TIME)
            _state.update { state ->
                state.copy(
                    isUserLoggedIn = isUserLoggedIn,
                    isLoading = false,
                )
            }
        }
    }

    companion object {
        private const val SPLASH_DISPLAY_TIME = 3000L
    }
}
