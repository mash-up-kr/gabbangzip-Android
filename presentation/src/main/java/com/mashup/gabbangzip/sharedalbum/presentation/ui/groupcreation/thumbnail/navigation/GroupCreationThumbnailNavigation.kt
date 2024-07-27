package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.GroupCreationState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.GroupCreationThumbnailScreen

fun NavController.navigateToGroupCreationThumbnail() {
    navigate(GroupCreationRoute.ThumbnailScreenRoute.route)
}

fun NavGraphBuilder.groupCreationThumbnailNavGraph(
    state: GroupCreationState,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onGetThumbnailButtonClicked: () -> Unit,
    navigateNextScreen: () -> Unit,
) {
    composable(route = GroupCreationRoute.ThumbnailScreenRoute.route) {
        GroupCreationThumbnailScreen(
            state = state,
            isGroupCreated = state.isGroupCreated,
            onBackButtonClicked = onBackButtonClicked,
            onNextButtonClicked = onNextButtonClicked,
            onGetThumbnailButtonClicked = onGetThumbnailButtonClicked,
            navigateNextScreen = navigateNextScreen,
        )
    }
}
