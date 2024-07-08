package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.GroupCreationNameScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute

fun NavController.navigateToGroupCreationName() {
    navigate(GroupCreationRoute.NameScreenRoute.route)
}

fun NavGraphBuilder.groupCreationNameNavGraph(
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    composable(route = GroupCreationRoute.NameScreenRoute.route) {
        GroupCreationNameScreen(
            onBackButtonClicked = onBackButtonClicked,
            onNextButtonClicked = onNextButtonClicked,
        )
    }
}