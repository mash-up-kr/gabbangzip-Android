package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

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
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import com.mashup.gabbangzip.sharedalbum.presentation.utils.FileUtil
import com.mashup.gabbangzip.sharedalbum.presentation.utils.PicPhotoPicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupCreationActivity : ComponentActivity() {
    private val viewModel by viewModels<GroupCreationViewModel>()
    private lateinit var photoPicker: PicPhotoPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        photoPicker = PicPhotoPicker.create(this@GroupCreationActivity) {
            viewModel.updateThumbnail(it)
        }

        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val groupCreationState by viewModel.uiState.collectAsStateWithLifecycle()
            SharedAlbumTheme {
                Scaffold(
                    snackbarHost = {
                        PicSnackbarHost(state = snackbarHostState)
                    },
                ) { contentPadding ->
                    GroupCreationNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                            .consumeWindowInsets(contentPadding)
                            .systemBarsPadding(),
                        navController = rememberNavController(),
                        startDestination = GroupCreationRoute.initRoute,
                        groupCreationUiState = groupCreationState,
                        onGetThumbnailButtonClicked = photoPicker::open,
                        updateName = viewModel::updateName,
                        updateKeyword = viewModel::updateKeyword,
                        createGroup = {
                            FileUtil.getFileFromUri(this, groupCreationState.thumbnail)
                                ?.let { imageFile ->
                                    viewModel.createGroup(
                                        name = groupCreationState.name,
                                        keyword = groupCreationState.keyword.name,
                                        file = imageFile,
                                    )
                                } ?: run {
                                viewModel.showSnackBar(
                                    type = PicSnackbarType.WARNING,
                                    message = R.string.image_retrieve_failed,
                                )
                            }
                        },
                        finishGroupCreation = {
                            MainActivity.openActivity(
                                context = this,
                                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP,
                            )
                            finish()
                        },
                        showSnackBarMessage = { type: PicSnackbarType, message: String ->
                            viewModel.showSnackBar(type, message)
                        },
                    )
                }
            }

            LaunchedEffect(true) {
                viewModel.effect.collect {
                    when (it) {
                        is GroupCreationViewModel.Event.ShowSnackBarMessage -> {
                            snackbarHostState.showPicSnackbar(
                                type = it.type,
                                message = it.message,
                            )
                        }

                        is GroupCreationViewModel.Event.ShowSnackBarMessageRes -> {
                            snackbarHostState.showPicSnackbar(
                                type = it.type,
                                message = ContextCompat.getString(
                                    this@GroupCreationActivity,
                                    it.message,
                                ),
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun openActivity(context: Context) {
            context.startActivity(
                Intent(context, GroupCreationActivity::class.java),
            )
        }
    }
}
