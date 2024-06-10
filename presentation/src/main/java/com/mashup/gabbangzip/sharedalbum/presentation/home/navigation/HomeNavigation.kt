package com.mashup.gabbangzip.sharedalbum.presentation.home.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.common.HomeRoute
import com.mashup.gabbangzip.sharedalbum.presentation.home.HomeScreen

fun NavController.navigateHome() {
    navigate(HomeRoute.route)
}

fun NavGraphBuilder.homeNavGraph(
    onGroupMakeClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    composable(route = HomeRoute.route) {
        HomeScreen(onGroupMakeClick)
        BackHandler {
            onBackPressed()
            Log.e("test", "hello world 2")
        }
    }
}
