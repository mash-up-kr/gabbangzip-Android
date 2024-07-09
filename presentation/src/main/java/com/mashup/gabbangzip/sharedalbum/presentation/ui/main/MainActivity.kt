package com.mashup.gabbangzip.sharedalbum.presentation.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.EventCreationActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }

            SharedAlbumTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    snackbarHost = {
                        PicSnackbarHost(state = snackbarHostState)
                    },
                ) { contentPadding ->
                    MainNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding),
                        navController = rememberNavController(),
                        startDestination = MainRoute.initRoute,
                        onClickEventMakeButton = {
                            EventCreationActivity.open(this@MainActivity)
                        },
                        onClickNotificationSetting = {
                            startActivity(
                                Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                                },
                            )
                        },
                        navigateLoginAndFinish = {
                            LoginActivity.openActivity(this@MainActivity)
                            finish()
                        },
                        showToastMessage = { message, type -> viewModel.showToastMessage(message, type) },
                    )
                }
            }

            LaunchedEffect(true) {
                viewModel.effect.collect {
                    when (it) {
                        is MainViewModel.Event.ShowToast -> {
                            snackbarHostState.showPicSnackbar(
                                type = it.snackbarType,
                                message = it.message,
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun openActivity(context: Activity) {
            context.startActivity(
                Intent(context, MainActivity::class.java),
            )
        }
    }
}
