package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.GroupCreationCompleteScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute

fun NavController.navigateToGroupCreationComplete() {
    navigate(GroupCreationRoute.CompleteScreenRoute.route)
}

fun NavGraphBuilder.groupCreationCompleteNavGraph(
    onNextButtonClicked: () -> Unit,
    showSnackBarMessage: (message: String) -> Unit,
) {
    composable(route = GroupCreationRoute.CompleteScreenRoute.route) {
        GroupCreationCompleteScreen(
            onNextButtonClicked = onNextButtonClicked,
            showSnackBarMessage = showSnackBarMessage,
        )
    }
}
