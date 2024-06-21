package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.sample.navigation.groupCreationFirstNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.sample.navigation.groupCreationSecondNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.sample.navigation.navigateGroupCreationSecond

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
        groupCreationFirstNavGraph(
            onClickNextButton = { navController.navigateGroupCreationSecond() },
        )
        groupCreationSecondNavGraph()
    }
}
