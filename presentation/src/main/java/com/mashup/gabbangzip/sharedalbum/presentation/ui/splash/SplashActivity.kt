package com.mashup.gabbangzip.sharedalbum.presentation.ui.splash

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.dark(
                TRANSPARENT,
            ),
        )
        viewModel.checkUserLoggedIn()
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()

            SharedAlbumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    SplashScreen()
                }

                when (state.isUserLoggedIn) {
                    true -> {
                        MainActivity.openActivity(this)
                        finish()
                    }

                    false -> {
                        LoginActivity.openActivity(this)
                        finish()
                    }

                    else -> {}
                }
            }
        }
    }
}
