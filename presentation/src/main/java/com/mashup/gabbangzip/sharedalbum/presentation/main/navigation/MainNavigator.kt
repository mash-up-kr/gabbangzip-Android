package com.mashup.gabbangzip.sharedalbum.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.common.Route
import com.mashup.gabbangzip.sharedalbum.presentation.groupMake.navigation.navigateGroupMake
import com.mashup.gabbangzip.sharedalbum.presentation.home.navigation.navigateHome

class MainNavigator(
    val navController: NavHostController,
) {
    val currentDestinationRoute: String
        get() = navController.currentBackStackEntry?.destination?.route ?: Route.initRoute
    val previousDestinationRoute: String
        get() = navController.previousBackStackEntry?.destination?.route ?: Route.initRoute

    fun navigateHome() {
        navController.navigateHome()
    }

    fun navigateGroupMake() {
        navController.navigateGroupMake()
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
