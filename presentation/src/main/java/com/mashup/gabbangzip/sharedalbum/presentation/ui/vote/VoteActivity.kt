package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation.VoteNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation.VoteNavRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation.navigateToVoteComplete
import com.mashup.gabbangzip.sharedalbum.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoteActivity : ComponentActivity() {
    private val viewModel: VoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
        )
        fetchVoteData()

        setContent {
            val state by viewModel.voteUiState.collectAsStateWithLifecycle()
            val navController = rememberNavController()
            if (state.isError) {
                showToast(R.string.error_retry)
            }

            SharedAlbumTheme {
                Scaffold { innerPadding ->
                    VoteNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        state = state,
                        navController = navController,
                        startDestination = VoteNavRoute.initRoute,
                        frameResId = PicPhotoFrame.getTypeByKeyword(state.voteResult.groupKeyword.name).frameResId,
                        thumbnailUrl = state.voteResult.randomImageUrl,
                        onDialogConfirm = { viewModel.updateVoteDialog(isVisible = false) },
                        onCancelVote = { viewModel.updateVoteDialog(isVisible = true) },
                        onVoteExit = { finish() },
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
                        onClickNavigationBack = { finish() },
                        onCompleteButtonClicked = { finish() },
                        onUploadPicture = { isUploadSuccess ->
                            if (isUploadSuccess) {
                                navController.navigateToVoteComplete()
                            } else {
                                showToast(R.string.error_retry)
                            }
                        },
                    )
                }
            }
        }
    }

    private fun fetchVoteData() {
        with(viewModel) {
            fetchUserInfo()
            fetchVotePhotoList(EVENT_ID)
        }
    }

    companion object {
        private const val EVENT_ID = "eventId"

        fun openActivity(context: Context, eventId: Long) {
            context.startActivity(
                Intent(context, VoteActivity::class.java).apply {
                    putExtra(EVENT_ID, eventId)
                },
            )
        }
    }
}
