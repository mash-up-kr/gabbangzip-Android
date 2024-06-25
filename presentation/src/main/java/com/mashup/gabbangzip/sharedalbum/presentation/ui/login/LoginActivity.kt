package com.mashup.gabbangzip.sharedalbum.presentation.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            SharedAlbumTheme {
                LoginScreen(
                    onClickLoginButton = {
                        viewModel.login()
                    },
                )

                if (state.errorMessage != null) {
                    Log.d(TAG, "${state.errorMessage}")
                    Toast.makeText(
                        this,
                        getString(R.string.login_failure_message),
                        Toast.LENGTH_SHORT,
                    )
                        .show()
                }

                if (state.isUserLoggedIn) {
                    MainActivity.open(this)
                    finish()
                }
            }
        }
    }

    companion object {
        private const val TAG = "LoginActivity"

        fun open(context: Activity) {
            context.startActivity(
                Intent(context, LoginActivity::class.java),
            )
        }
    }
}
