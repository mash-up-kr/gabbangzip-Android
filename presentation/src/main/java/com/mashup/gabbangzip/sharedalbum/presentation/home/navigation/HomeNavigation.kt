package com.mashup.gabbangzip.sharedalbum.presentation.home.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.common.BaseRoute
import com.mashup.gabbangzip.sharedalbum.presentation.home.HomeScreen

fun NavController.navigateHome() {
    navigate(HomeRoute.route)
}

fun NavGraphBuilder.homeNavGraph(
    onGroupMakeClick: () -> Unit,
    onBackPressed: () -> Unit,
) {
    composable(route = HomeRoute.route) {
        HomeScreen(onGroupMakeClick)
        BackHandler {
            onBackPressed()
        }
    }
}

data object HomeRoute : BaseRoute {
    override val route: String = "home"
}
