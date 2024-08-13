package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.PhotoVoteScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.VoteCompleteScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType

fun NavController.navigateToVote() {
    navigate(VoteNavRoute.VoteRoute.route)
}

fun NavController.navigateToVoteComplete() {
    navigate(VoteNavRoute.VoteCompleteRoute.route)
}

fun NavGraphBuilder.voteNavGraph(
    state: PhotoVoteState,
    onDialogConfirm: () -> Unit,
    onCancelVote: () -> Unit,
    onVoteExit: () -> Unit,
    onVoteBySwiped: (voteType: PhotoVoteType, photo: Photo) -> Unit,
    onVoteByClicked: (result: PhotoVoteType, photo: Photo) -> Unit,
    onVoteClick: (voteType: PhotoVoteType) -> Unit,
    onSwipeFinish: () -> Unit,
    onUploadPicture: (Boolean) -> Unit,
) {
    composable(route = VoteNavRoute.VoteRoute.route) {
        PhotoVoteScreen(
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
    }
}

fun NavGraphBuilder.voteCompleteNavGraph(
    @DrawableRes frameResId: Int,
    thumbnailUrl: String,
    onCompleteButtonClicked: () -> Unit,
    onClickNavigationBack: () -> Unit,
) {
    composable(route = VoteNavRoute.VoteCompleteRoute.route) {
        VoteCompleteScreen(
            frameResId = frameResId,
            thumbnailUrl = thumbnailUrl,
            onCompleteButtonClicked = onCompleteButtonClicked,
            onClickNavigationBack = onClickNavigationBack,
        )
    }
}
