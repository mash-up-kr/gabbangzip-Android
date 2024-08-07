package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoVoteScreen(
                onVoteFinish = { /*TODO*/ },
            )
        }
    }

    companion object {
        fun openActivity(context: Context) {
            context.startActivity(
                Intent(context, VoteActivity::class.java),
            )
        }
    }
}
