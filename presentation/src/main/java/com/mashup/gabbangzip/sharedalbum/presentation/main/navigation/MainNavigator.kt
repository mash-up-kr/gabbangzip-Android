package com.mashup.gabbangzip.sharedalbum.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.gabbangzip.sharedalbum.presentation.groupMake.navigation.navigateGroupMake
import com.mashup.gabbangzip.sharedalbum.presentation.home.navigation.navigateHome

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

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
