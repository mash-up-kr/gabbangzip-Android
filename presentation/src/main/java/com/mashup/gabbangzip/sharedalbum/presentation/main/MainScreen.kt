package com.mashup.gabbangzip.sharedalbum.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.common.StartDestination
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.TopBar
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.TopBarState
import com.mashup.gabbangzip.sharedalbum.presentation.groupMake.navigation.groupMakeNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.home.navigation.homeNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.login.navigation.loginNavGraph

@Composable
fun MainScreen(
    navigator: MainNavigator,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val topBarState by viewModel.topBarStateFlow.collectAsState()

    Scaffold(
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)
            ) {
                TopBar(
                    topBarState = topBarState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
                NavHost(
                    navController = navigator.navController,
                    startDestination = StartDestination.ROUTE,
                    modifier = Modifier.weight(weight = 1f)
                ) {
                    loginNavGraph(
                        onLoginClick = {
                            navigator.navigateHome()
                            viewModel.updateTopBarState(
                                topBarState = TopBarState.Main(
                                    titleText = "홈 화면",
                                    iconRes = R.drawable.ic_launcher_foreground
                                )
                            )
                        },
                        onBackPressed = {
                            navigator.navController.popBackStack()
                            viewModel.popBackTopBarState()
                        }
                    )
                    homeNavGraph(
                        onGroupMakeClick = {
                            navigator.navigateGroupMake()
                            viewModel.updateTopBarState(
                                topBarState = TopBarState.Progress(
                                    titleText = "그룹 만들기",
                                    max = 3,
                                    progress = 1
                                )
                            )
                        },
                        onBackPressed = {
                            navigator.navController.popBackStack()
                            viewModel.popBackTopBarState()
                        }
                    )
                    groupMakeNavGraph(
                        onBackPressed = {
                            navigator.navController.popBackStack()
                            viewModel.popBackTopBarState()
                        }
                    )
                }
            }
        },
    )
}
