package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

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
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
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
            val groupCreationState by viewModel.uiState.collectAsStateWithLifecycle()
            SharedAlbumTheme {
                Scaffold { contentPadding ->
                    GroupCreationNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Gray0)
                            .padding(contentPadding)
                            .consumeWindowInsets(contentPadding)
                            .systemBarsPadding(),
                        navController = rememberNavController(),
                        startDestination = GroupCreationRoute.initRoute,
                        groupCreationState = groupCreationState,
                        onGetThumbnailButtonClicked = photoPicker::open,
                        updateName = viewModel::updateName,
                        updateKeyword = viewModel::updateKeyword,
                        onNextButtonClicked = { finish() },
                        onCopyLinkButton = { }
                    )
                }
            }
        }
    }

    companion object {
        fun openActivity(context: Activity) {
            context.startActivity(
                Intent(context, GroupCreationActivity::class.java),
            )
        }
    }
}
