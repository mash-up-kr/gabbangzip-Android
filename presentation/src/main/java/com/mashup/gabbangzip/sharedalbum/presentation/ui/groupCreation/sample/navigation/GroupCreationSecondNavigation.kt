package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.sample.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.sample.GroupCreationSecondScreen

fun NavController.navigateGroupCreationSecond() {
    navigate(GroupCreationRoute.SecondScreenRoute.route)
}

fun NavGraphBuilder.groupCreationSecondNavGraph() {
    composable(route = GroupCreationRoute.SecondScreenRoute.route) {
        GroupCreationSecondScreen()
    }
}
