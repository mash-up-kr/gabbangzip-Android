package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupCreationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedAlbumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    GroupCreationScreen()
                }
            }
        }
    }

    companion object {
        fun open(context: Activity) {
            context.startActivity(
                Intent(context, GroupCreationActivity::class.java),
            )
        }
    }
}
