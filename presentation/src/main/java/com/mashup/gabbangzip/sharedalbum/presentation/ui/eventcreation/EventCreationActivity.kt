package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.content.Context
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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.utils.FileUtil
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
            val snackbarHostState = remember { SnackbarHostState() }
            SharedAlbumTheme {
                Scaffold(
                    snackbarHost = {
                        PicSnackbarHost(state = snackbarHostState)
                    },
                ) { contentPadding ->
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
                        onCompleteButtonClicked = { summary ->
                            with(eventCreationViewModel) {
                                eventCreationState.pictures.forEach { uri ->
                                    val file =
                                        FileUtil.getFileFromUri(this@EventCreationActivity, uri)
                                    addPictureFile(file)
                                }
                                if (checkValidState()) {
                                    createEvent(summary)
                                } else {
                                    showSnackBar()
                                    clearPictures()
                                }
                            }
                        },
                        onGalleryButtonClicked = photoPicker::open,
                        onPictureDeleteButtonClicked = eventCreationViewModel::deletePicture,
                    )
                }

                LaunchedEffect(true) {
                    eventCreationViewModel.eventFlow.collect {
                        when (it) {
                            is EventCreationEvent.Error -> {
                                snackbarHostState.showPicSnackbar(
                                    type = PicSnackbarType.WARNING,
                                    message = getString(R.string.image_retrieve_failed),
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val PICTURES_MAX_COUNT = 4

        fun openActivity(context: Context) {
            context.startActivity(
                Intent(context, EventCreationActivity::class.java),
            )
        }
    }
}
