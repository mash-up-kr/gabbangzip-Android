package com.mashup.gabbangzip.sharedalbum.presentation.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.model.LoginParam
import com.mashup.gabbangzip.sharedalbum.domain.usecase.LoginUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.auth.KakaoUserSdkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login() {
        KakaoUserSdkUtil.loginWithKakao(
            context = context,
            onSuccess = { idToken, profile ->
                val nickname = profile.nickname
                val profileImage = profile.profileImageUrl
                if (nickname != null && profileImage != null) {
                    picLogin(
                        idToken = idToken,
                        nickname = nickname,
                        profileImage = profileImage,
                    )
                } else {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = "정보 조회 실패",
                        )
                    }
                }
            },
            onFailure = {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMessage = "카카오 로그인 실패 또는 정보 조회 실패 $it",
                    )
                }
            },
        )
    }

    private fun picLogin(idToken: String, nickname: String, profileImage: String) {
        val param = LoginParam(
            idToken = idToken,
            nickname = nickname,
            profileImage = profileImage,
        )
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    errorMessage = null,
                )
            }
            loginUseCase(param)
                .onSuccess {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = null,
                            isUserLoggedIn = true,
                        )
                    }
                }.onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = "로그인 실패 $it",
                        )
                    }
                }
        }
    }
}
