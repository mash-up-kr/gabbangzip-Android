package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation.navigation.EventCreationNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation.navigation.EventCreationRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventCreationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedAlbumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    EventCreationNavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = rememberNavController(),
                        startDestination = EventCreationRoute.initRoute
                    )
                }
            }
        }
    }

    companion object {
        fun open(context: Activity) {
            context.startActivity(
                Intent(context, EventCreationActivity::class.java)
            )
        }
    }
}
