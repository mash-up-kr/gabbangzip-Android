package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
            max = PICTURES_MAX_COUNT,
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
                            .padding(contentPadding)
                            .consumeWindowInsets(contentPadding)
                            .systemBarsPadding(),
                        navController = rememberNavController(),
                        startDestination = EventCreationRoute.initRoute,
                        eventCreationState = eventCreationState,
                        clearEventCreationState = eventCreationViewModel::clearEventCreationState,
                        onCompleteButtonClicked = eventCreationViewModel::createEvent,
                        onGalleryButtonClicked = photoPicker::open,
                        onPictureDeleteButtonClicked = eventCreationViewModel::deletePicture,
                    )
                }
            }
        }
    }

    companion object {
        const val PICTURES_MAX_COUNT = 4

        fun open(context: Activity) {
            context.startActivity(
                Intent(context, EventCreationActivity::class.java),
            )
        }
    }
}
