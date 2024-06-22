package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.sample.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.sample.GroupCreationSecondScreen

fun NavController.navigateGroupCreationSecond() {
    navigate(GroupCreationRoute.SecondScreenRoute.route)
}

fun NavGraphBuilder.groupCreationSecondNavGraph() {
    composable(route = GroupCreationRoute.SecondScreenRoute.route) {
        GroupCreationSecondScreen()
    }
}
