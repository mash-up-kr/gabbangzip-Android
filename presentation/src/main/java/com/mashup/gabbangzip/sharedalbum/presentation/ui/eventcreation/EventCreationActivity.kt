package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import com.mashup.gabbangzip.sharedalbum.presentation.utils.FileUtil
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.PicPhotoPicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventCreationActivity : ComponentActivity() {
    private val eventCreationViewModel: EventCreationViewModel by viewModels()
    private lateinit var photoPicker: PicPhotoPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
        )
        photoPicker = PicPhotoPicker.create(
            activity = this@EventCreationActivity,
            max = PICTURES_MAX_COUNT,
            onImagePicked = { uriList ->
                eventCreationViewModel.updatePictures(uriList)
            },
        )
        setContent {
            val state by eventCreationViewModel.uiState.collectAsStateWithLifecycle()
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
                        eventCreationState = state,
                        clearEventCreationState = eventCreationViewModel::clearEventCreationState,
                        updateDate = eventCreationViewModel::updateDate,
                        onGalleryButtonClicked = photoPicker::open,
                        onPictureDeleteButtonClicked = eventCreationViewModel::deletePicture,
                        onCompleteButtonClicked = { description ->
                            checkCreation(state.pictures, description)
                        },
                        onBackButtonClicked = { finish() },
                    )
                }

                val groupId = state.eventCreationSuccess
                if (groupId != null) {
                    MainActivity.openActivity(
                        context = this@EventCreationActivity,
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP,
                        groupId = groupId,
                    )
                    finish()
                }
                ObserveEvent(snackbarHostState)
            }
        }
    }

    @Composable
    private fun ObserveEvent(snackbarHostState: SnackbarHostState) {
        LaunchedEffect(null) {
            eventCreationViewModel.eventFlow.collect {
                when (it) {
                    is EventCreationEvent.Error -> {
                        snackbarHostState.showPicSnackbar(
                            type = PicSnackbarType.WARNING,
                            message = getString(R.string.image_retrieve_failed),
                        )
                    }

                    is EventCreationEvent.OverflowImageError -> {
                        snackbarHostState.showPicSnackbar(
                            type = PicSnackbarType.WARNING,
                            message = getString(R.string.image_overflow_failed)
                                .format(PICTURES_MAX_COUNT),
                        )
                    }
                }
            }
        }
    }

    private fun checkCreation(pictures: ImmutableList<Uri?>, description: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            pictures.mapNotNull { uri ->
                uri?.let {
                    async {
                        FileUtil.getJpgImage(
                            this@EventCreationActivity,
                            uri,
                        )
                    }
                }
            }.also {
                eventCreationViewModel.checkEventCreation(
                    description,
                    it.awaitAll(),
                )
            }
        }
    }

    companion object {
        const val PICTURES_MAX_COUNT = 4
        const val INTENT_EXTRA_GROUP_ID = "groupId"

        fun openActivity(context: Context, groupId: Long) {
            context.startActivity(
                Intent(context, EventCreationActivity::class.java).apply {
                    putExtra(INTENT_EXTRA_GROUP_ID, groupId)
                },
            )
        }
    }
}
