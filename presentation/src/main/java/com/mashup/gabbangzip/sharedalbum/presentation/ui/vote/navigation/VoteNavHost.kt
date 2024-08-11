package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType

@Composable
fun VoteNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    state: PhotoVoteState,
    onDialogConfirm: () -> Unit,
    onCancelVote: () -> Unit,
    onVoteExit: () -> Unit,
    onVoteBySwiped: (voteType: PhotoVoteType, photo: Photo) -> Unit,
    onVoteByClicked: (result: PhotoVoteType, photo: Photo) -> Unit,
    onVoteClick: (voteType: PhotoVoteType) -> Unit,
    onSwipeFinish: () -> Unit,
    onCompleteButtonClicked: () -> Unit,
    onClickNavigationBack: () -> Unit,
    onUploadPicture: (Boolean) -> Unit,
    frameResId: Int,
    thumbnailUrl: String,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        voteNavGraph(
            state = state,
            onDialogConfirm = onDialogConfirm,
            onCancelVote = onCancelVote,
            onVoteExit = onVoteExit,
            onVoteBySwiped = onVoteBySwiped,
            onVoteByClicked = onVoteByClicked,
            onVoteClick = onVoteClick,
            onSwipeFinish = onSwipeFinish,
            onUploadPicture = onUploadPicture,
        )

        voteCompleteNavGraph(
            frameResId = frameResId,
            thumbnailUrl = thumbnailUrl,
            onCompleteButtonClicked = onCompleteButtonClicked,
            onClickNavigationBack = onClickNavigationBack,
        )
    }
}
