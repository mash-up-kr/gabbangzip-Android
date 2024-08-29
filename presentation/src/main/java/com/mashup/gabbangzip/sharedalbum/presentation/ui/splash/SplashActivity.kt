package com.mashup.gabbangzip.sharedalbum.presentation.ui.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color.TRANSPARENT
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val viewModel by viewModels<SplashViewModel>()
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) {
        viewModel.checkUserLoggedIn()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNotificationPermission()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.dark(
                TRANSPARENT,
            ),
        )
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

    @SuppressLint("InlinedApi")
    private fun checkNotificationPermission() {
        if (askNotificationPermission()) {
            viewModel.checkUserLoggedIn()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun askNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
    }
}
