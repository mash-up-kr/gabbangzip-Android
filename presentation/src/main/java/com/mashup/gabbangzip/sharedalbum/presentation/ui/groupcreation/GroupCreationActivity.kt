package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.utils.PicPhotoPicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupCreationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val pictureUriFlow = MutableStateFlow<Uri?>(null)
        val photoPicker = PicPhotoPicker.create(this@GroupCreationActivity) {
            lifecycleScope.launch {
                if (it != null) {
                    pictureUriFlow.emit(it)
                }
            }
        }

        setContent {
            val pictureUri by pictureUriFlow.collectAsState()
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
                        thumbnailUri = pictureUri,
                        onGetThumbnailButtonClicked = photoPicker::open,
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
