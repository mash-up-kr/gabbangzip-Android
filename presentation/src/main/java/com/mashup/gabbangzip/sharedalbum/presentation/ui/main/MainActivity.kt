package com.mashup.gabbangzip.sharedalbum.presentation.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.utils.PicPhotoPicker
import com.mashup.gabbangzip.sharedalbum.presentation.utils.shareBitmap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var photoPicker: PicPhotoPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.registerFcmToken()
        initPhotoPicker()

        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()
            SharedAlbumTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { PicSnackbarHost(state = snackbarHostState) },
                ) { innerPadding ->
                    MainNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = rememberNavController(),
                        startDestination = MainRoute.initRoute,
                        navigateLoginAndFinish = {
                            LoginActivity.openActivity(this@MainActivity)
                            finish()
                        },
                        onClickOpenPhotoPickerButton = {
                            photoPicker.open()
                        },
                        onClickPokeButton = viewModel::pokeOtherUser,
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
            }
        }
    }

    private fun initPhotoPicker() {
        photoPicker = PicPhotoPicker.create(this) { uri ->
            if (uri != null) {
                viewModel.uploadMyPic(uri)
            }
        }
    }

    companion object {
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
    }
}
