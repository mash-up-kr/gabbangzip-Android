package com.mashup.gabbangzip.sharedalbum.presentation.groupMake.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.common.BaseRoute
import com.mashup.gabbangzip.sharedalbum.presentation.groupMake.GroupMakeScreen

fun NavController.navigateGroupMake() {
    navigate(GroupMakeRoute.route)
}

fun NavGraphBuilder.groupMakeNavGraph(
    onBackPressed: () -> Unit,
) {
    composable(route = GroupMakeRoute.route) {
        GroupMakeScreen()
        BackHandler {
            onBackPressed()
        }
    }
}

data object GroupMakeRoute : BaseRoute {
    override val route: String = "groupMake"
}
