package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.sample.navigation.groupCreationSecondNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.sample.navigation.navigateGroupCreationSecond
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.start.navigation.groupCreationStartNavGraph

@Composable
fun GroupCreationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        groupCreationStartNavGraph(
            onClickNextButton = { navController.navigateGroupCreationSecond() },
        )
        groupCreationSecondNavGraph(
            onClickBackButton = { navController.popBackStack() },
        )
    }
}
