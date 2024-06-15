package com.mashup.gabbangzip.sharedalbum.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.TopBarState
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
                navigator.navigateHome()
                viewModel.updateTopBarState(
                    topBarState = TopBarState.Main(
                        titleText = "홈 화면",
                        iconRes = R.drawable.ic_launcher_foreground,
                    ),
                )
            },
            onBackPressed = {
                navigator.navController.popBackStack()
                viewModel.popBackTopBarState()
            },
        )
        homeNavGraph(
            onGroupMakeClick = {
                navigator.navigateGroupMake()
                viewModel.updateTopBarState(
                    topBarState = TopBarState.Progress(
                        titleText = "그룹 만들기",
                        max = 3,
                        progress = 1,
                    ),
                )
            },
            onBackPressed = {
                navigator.navController.popBackStack()
                viewModel.popBackTopBarState()
            },
        )
        groupMakeNavGraph(
            onBackPressed = {
                navigator.navController.popBackStack()
                viewModel.popBackTopBarState()
            },
        )
    }
}
