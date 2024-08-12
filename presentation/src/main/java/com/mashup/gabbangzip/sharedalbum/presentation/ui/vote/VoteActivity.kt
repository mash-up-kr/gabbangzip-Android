package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation.VoteNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation.VoteNavRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation.navigateToVoteComplete
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoteActivity : ComponentActivity() {
    private val viewModel: VoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchUserName()

        setContent {
            val state by viewModel.voteUiState.collectAsStateWithLifecycle()
            val navController = rememberNavController()

            VoteNavHost(
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
                        // TODO: 향후 논의해서 업로드 실패시 에러토스트..? 스낵바..? 정하기.
                    }
                },
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
