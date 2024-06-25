package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }

    fun withdrawal() {
        // Todo : 회원탈퇴 로직 작성하기
    }
}
