package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.sample.navigation.eventCreationFirstNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.sample.navigation.eventCreationSecondNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.sample.navigation.navigateEventCreationSecond

@Composable
fun EventCreationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        eventCreationFirstNavGraph(
            onClickNextButton = { navController.navigateEventCreationSecond() },
        )
        eventCreationSecondNavGraph(
            onClickBackButton = { navController.popBackStack() },
        )
    }
}
