package com.mashup.gabbangzip.sharedalbum.presentation.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicLoadingIndicator
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.GroupCreationActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.navigation.navigateGroupDetail
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.model.MainEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.utils.FileUtil
import com.mashup.gabbangzip.sharedalbum.presentation.utils.PicPhotoPicker
import com.mashup.gabbangzip.sharedalbum.presentation.utils.shareBitmap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var photoPicker: PicPhotoPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.light(
                TRANSPARENT,
                TRANSPARENT,
            ),
        )
        viewModel.registerFcmToken()
        initPhotoPicker()

        setContent {
            val state by viewModel.mainState.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()

            SharedAlbumTheme {
                ObserveEvent(snackbarHostState)
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    snackbarHost = { PicSnackbarHost(state = snackbarHostState) },
                ) { innerPadding ->
                    MainNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .consumeWindowInsets(innerPadding)
                            .systemBarsPadding(),
                        innerPadding = innerPadding,
                        navController = navController,
                        startDestination = MainRoute.initRoute,
                        navigateLoginAndFinish = {
                            LoginActivity.openActivity(this)
                            finish()
                        },
                        navigateToGroupCreationAndFinish = {
                            GroupCreationActivity.openActivity(this)
                            finish()
                        },
                        onClickOpenPhotoPickerButton = { eventId ->
                            viewModel.setCurrentEventId(eventId)
                            photoPicker.open()
                        },
                        onClickSendFcmButton = { eventId ->
                            viewModel.sendKookNotification(eventId)
                        },
                        onClickShareButton = { bitmap ->
                            shareBitmap(bitmap)
                        },
                        onSnackbarRequired = { type, message ->
                            coroutineScope.launch {
                                snackbarHostState.showPicSnackbar(type, message)
                            }
                        },
                    )
                }
                LaunchedEffect(true) {
                    val groupId = intent.getLongExtra(INTENT_EXTRA_GROUP_ID, -1)
                    if (groupId > -1) {
                        navController.navigateGroupDetail(groupId)
                    }
                }

                PicLoadingIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Gray0),
                    isVisible = state.isLoading,
                )
            }
        }
    }

    private fun initPhotoPicker() {
        photoPicker = PicPhotoPicker.create(
            activity = this,
            max = PICTURES_MAX_COUNT,
        ) { uriList ->
            lifecycleScope.launch {
                uriList.mapNotNull { uri ->
                    uri?.let {
                        async(Dispatchers.IO) {
                            FileUtil.getJpgImage(this@MainActivity, it)
                        }
                    }
                }.also { viewModel.uploadMyPic(it.awaitAll()) }
            }
        }
    }

    @Composable
    private fun ObserveEvent(snackbarHostState: SnackbarHostState) {
        val errorRetryMessage = stringResource(id = R.string.error_retry)
        LaunchedEffect(null) {
            viewModel.mainEvent.collect { event ->
                when (event) {
                    MainEvent.SuccessNotification -> snackbarHostState.showPicSnackbar(
                        type = PicSnackbarType.NORMAL,
                        message = getString(R.string.kook_snackbar),
                    )

                    MainEvent.FailNotification -> snackbarHostState.showPicSnackbar(
                        type = PicSnackbarType.WARNING,
                        message = errorRetryMessage,
                    )

                    MainEvent.SuccessUploadMyPic -> snackbarHostState.showPicSnackbar(
                        type = PicSnackbarType.CHECK,
                        message = getString(R.string.my_pic_upload_complete),
                    )

                    MainEvent.FailUploadMyPic -> snackbarHostState.showPicSnackbar(
                        type = PicSnackbarType.WARNING,
                        message = errorRetryMessage,
                    )
                }
            }
        }
    }

    companion object {
        const val PICTURES_MAX_COUNT = 4
        const val INTENT_EXTRA_GROUP_ID = "groupId"

        fun openActivity(context: Activity) {
            context.startActivity(
                Intent(context, MainActivity::class.java),
            )
        }

        fun openActivity(context: Activity, flags: Int) {
            context.startActivity(
                Intent(context, MainActivity::class.java).apply {
                    addFlags(flags)
                },
            )
        }

        fun openActivity(context: Activity, flags: Int, groupId: Long) {
            context.startActivity(
                Intent(context, MainActivity::class.java).apply {
                    addFlags(flags)
                    putExtra(INTENT_EXTRA_GROUP_ID, groupId)
                },
            )
        }
    }
}
