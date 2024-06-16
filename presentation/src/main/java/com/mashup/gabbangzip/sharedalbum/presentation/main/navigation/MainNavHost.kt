package com.mashup.gabbangzip.sharedalbum.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.common.GroupMakeRoute
import com.mashup.gabbangzip.sharedalbum.presentation.common.HomeRoute
import com.mashup.gabbangzip.sharedalbum.presentation.groupMake.navigation.groupMakeNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.home.navigation.homeNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.login.navigation.loginNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.main.MainViewModel

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    startDestination: String,
    viewModel: MainViewModel,
) {
    NavHost(
        modifier = modifier,
        navController = navigator.navController,
        startDestination = startDestination,
    ) {
        loginNavGraph(
            onLoginClick = {
                viewModel.updateTopBar(HomeRoute)
                navigator.navigateHome()
            },
            onBackPressed = {
                viewModel.updateTopBar(navigator.previousDestinationRoute)
                navigator.navController.popBackStack()
            },
        )
        homeNavGraph(
            onGroupMakeClick = {
                viewModel.updateTopBar(GroupMakeRoute)
                navigator.navigateGroupMake()
            },
            onBackPressed = {
                viewModel.updateTopBar(navigator.previousDestinationRoute)
                navigator.navController.popBackStack()
            },
        )
        groupMakeNavGraph(
            onBackPressed = {
                viewModel.updateTopBar(navigator.previousDestinationRoute)
                navigator.navController.popBackStack()
            },
        )
    }
}
