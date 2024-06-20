package com.mashup.gabbangzip.sharedalbum.presentation.ui.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedAlbumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    SplashScreen()
                }
            }
        }
        coroutineScope.launch {
            delay(SPLASH_DISPLAY_TIME)
            LoginActivity.open(this@SplashActivity)
            finish()
        }
    }

    companion object {
        private const val SPLASH_DISPLAY_TIME = 3000L // 3초
    }
}
