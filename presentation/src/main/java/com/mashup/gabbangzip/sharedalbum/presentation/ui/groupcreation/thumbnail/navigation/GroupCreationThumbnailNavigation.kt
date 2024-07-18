package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.GroupCreationThumbnailScreen

fun NavController.navigateToGroupCreationThumbnail() {
    navigate(GroupCreationRoute.ThumbnailScreenRoute.route)
}

fun NavGraphBuilder.groupCreationThumbnailNavGraph(
    initialThumbnail: Uri?,
    isGroupCreated: Boolean,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onGetThumbnailButtonClicked: () -> Unit,
    navigateNextScreen: () -> Unit,
) {
    composable(route = GroupCreationRoute.ThumbnailScreenRoute.route) {
        GroupCreationThumbnailScreen(
            initialThumbnail = initialThumbnail,
            isGroupCreated = isGroupCreated,
            onBackButtonClicked = onBackButtonClicked,
            onNextButtonClicked = onNextButtonClicked,
            onGetThumbnailButtonClicked = onGetThumbnailButtonClicked,
            navigateNextScreen = navigateNextScreen,
        )
    }
}
