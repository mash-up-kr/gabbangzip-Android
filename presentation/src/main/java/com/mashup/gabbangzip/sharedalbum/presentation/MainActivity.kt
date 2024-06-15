package com.mashup.gabbangzip.sharedalbum.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mashup.gabbangzip.sharedalbum.presentation.main.MainScreen
import com.mashup.gabbangzip.sharedalbum.presentation.main.navigation.MainNavigator
import com.mashup.gabbangzip.sharedalbum.presentation.main.navigation.rememberMainNavigator
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            SharedAlbumTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(
                    navigator = navigator,
                )
            }
        }
    }
}
