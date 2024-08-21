package com.mashup.gabbangzip.sharedalbum.presentation.ui.login

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.auth.KakaoUserSdkUtil
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
        )
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }

            SharedAlbumTheme {
                Scaffold(
                    snackbarHost = {
                        PicSnackbarHost(state = snackbarHostState)
                    },
                ) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        LoginScreen(
                            onClickLoginButton = {
                                KakaoUserSdkUtil.loginWithKakao(
                                    context = this@LoginActivity,
                                    onSuccess = viewModel::kakaoLoginSuccess,
                                    onFailure = viewModel::kakaoLoginFailure,
                                )
                            },
                        )
                    }
                }

                if (state.errorMessage != null) {
                    Log.d(TAG, "${state.errorMessage}")
                    LaunchedEffect(snackbarHostState) {
                        snackbarHostState.showPicSnackbar(
                            type = PicSnackbarType.WARNING,
                            message = getString(R.string.login_failure_message),
                        )
                    }
                }

                if (state.isUserLoggedIn) {
                    MainActivity.openActivity(this)
                    finish()
                }
            }
        }
    }

    companion object {
        private const val TAG = "LoginActivity"

        fun openActivity(context: Activity) {
            context.startActivity(
                Intent(context, LoginActivity::class.java),
            )
        }
    }
}
