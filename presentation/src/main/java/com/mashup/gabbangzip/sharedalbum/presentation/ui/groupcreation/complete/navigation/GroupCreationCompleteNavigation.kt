package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.GroupCreationCompleteScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute

fun NavController.navigateToGroupCreationComplete() {
    navigate(GroupCreationRoute.CompleteScreenRoute.route)
}

fun NavGraphBuilder.groupCreationCompleteNavGraph(
    onNextButtonClicked: () -> Unit,
    showSnackbarMessage: (type: PicSnackbarType, message: String) -> Unit,
) {
    composable(route = GroupCreationRoute.CompleteScreenRoute.route) {
        GroupCreationCompleteScreen(
            onNextButtonClicked = onNextButtonClicked,
            showSnackbarMessage = showSnackbarMessage,
        )
    }
}