package com.mashup.gabbangzip.sharedalbum.presentation.ui.main

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
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation.EventCreationActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.GroupCreationActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupList.GroupListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedAlbumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    GroupListScreen(
                        onClickEventMakeButton = {
                            EventCreationActivity.open(this@MainActivity)
                        },
                    )
                }
            }
        }
    }

    companion object {
        fun open(context: Activity) {
            context.startActivity(
                Intent(context, MainActivity::class.java)
            )
        }
    }
}
