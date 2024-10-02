package com.mashup.gabbangzip.sharedalbum.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.config.SaveIsFirstOpenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveIsFirstOpenUseCase: SaveIsFirstOpenUseCase,
) : ViewModel() {
    fun saveIsNotFirstOpen() {
        viewModelScope.launch {
            saveIsFirstOpenUseCase(false)
        }
    }
}