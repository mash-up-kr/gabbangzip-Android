package com.mashup.gabbangzip.sharedalbum.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.common.StartDestination
import com.mashup.gabbangzip.sharedalbum.presentation.groupMake.navigation.groupMakeNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.home.navigation.homeNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.login.navigation.loginNavGraph

@Composable
fun MainScreen(
    navigator: MainNavigator,
) {
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = StartDestination.ROUTE,
                    modifier = Modifier.weight(weight = 1f)
                ) {
                    loginNavGraph(
                        onLoginClick = {
                            navigator.navigateHome()
                        },
                        onBackPressed = {
                            navigator.navController.popBackStack()
                        }
                    )
                    homeNavGraph(
                        onGroupMakeClick = {
                            navigator.navigateGroupMake()
                        },
                        onBackPressed = {
                            navigator.navController.popBackStack()
                        }
                    )
                    groupMakeNavGraph(
                        onBackPressed = {
                            navigator.navController.popBackStack()
                        }
                    )
                }
            }
        },
    )
}
