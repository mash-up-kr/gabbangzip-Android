package com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvitationCodeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SharedAlbumTheme {
                InvitationCodeScreen(
                    onBackButtonClicked = { finish() },
                    onNextButtonClicked = { /* TODO */ },
                )
            }
        }
    }

    companion object {
        private const val TAG = "InvitationCodeActivity"

        fun openActivity(context: Activity) {
            context.startActivity(
                Intent(context, InvitationCodeActivity::class.java),
            )
        }
    }
}
