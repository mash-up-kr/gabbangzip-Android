package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.utils.PicPhotoPicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventCreationActivity : ComponentActivity() {
    private val eventCreationViewModel: EventCreationViewModel by viewModels()
    private lateinit var photoPicker: PicPhotoPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        photoPicker = PicPhotoPicker.create(
            activity = this@EventCreationActivity,
            max = 4,
            onImagePicked = { uriList ->
                eventCreationViewModel.updatePictures(uriList)
            },
        )
        setContent {
            val eventCreationState by eventCreationViewModel.uiState.collectAsStateWithLifecycle()
            SharedAlbumTheme {
                Scaffold { contentPadding ->
                    EventCreationNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Gray0)
                            .padding(contentPadding)
                            .consumeWindowInsets(contentPadding)
                            .systemBarsPadding(),
                        navController = rememberNavController(),
                        startDestination = EventCreationRoute.initRoute,
                        eventCreationState = eventCreationState,
                        onCompleteButtonClicked = {
                            //TODO: summary 어떻게할까 고민
                            eventCreationViewModel::createEvent
                        },
                        onGalleryButtonClicked = photoPicker::open,
                    )
                }
            }
        }
    }

    companion object {
        fun open(context: Activity) {
            context.startActivity(
                Intent(context, EventCreationActivity::class.java),
            )
        }
    }
}
