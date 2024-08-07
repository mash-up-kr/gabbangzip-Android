package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoteActivity : ComponentActivity() {
    private val viewModel: VoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.voteUiState.collectAsStateWithLifecycle()

            PhotoVoteScreen(
                state = state,
                onDialogConfirm = { viewModel.updateVoteDialog(isVisible = false) },
                onCancelVote = { viewModel.updateVoteDialog(isVisible = true) },
                onVoteExit = {
                    MainActivity.openActivity(this@VoteActivity)
                    finish()
                },
                onVoteBySwiped = { voteType, photo ->
                    viewModel.updateVoteEvent(voteType)
                    viewModel.addVoteResult(voteType, photo)
                },
                onVoteByClicked = { voteType, photo ->
                    viewModel.addVoteResult(voteType, photo)
                },
                onVoteClick = { voteType ->
                    viewModel.updateVoteEvent(voteType)
                },
                onSwipeFinish = { viewModel.finishVote() },
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
