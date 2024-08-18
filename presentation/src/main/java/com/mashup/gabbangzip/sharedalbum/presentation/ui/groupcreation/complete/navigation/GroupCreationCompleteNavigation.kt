package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.GroupCreationCompleteScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.model.GroupCreationResult
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute

fun NavController.navigateToGroupCreationComplete() {
    navigate(GroupCreationRoute.CompleteScreenRoute.route) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.groupCreationCompleteNavGraph(
    groupCreationResult: GroupCreationResult?,
    onNextButtonClicked: () -> Unit,
    showSnackBarMessage: (type: PicSnackbarType, message: String) -> Unit,
) {
    composable(route = GroupCreationRoute.CompleteScreenRoute.route) {
        GroupCreationCompleteScreen(
            groupCreationResult = groupCreationResult,
            onNextButtonClicked = onNextButtonClicked,
            showSnackBarMessage = showSnackBarMessage,
        )
    }
}
