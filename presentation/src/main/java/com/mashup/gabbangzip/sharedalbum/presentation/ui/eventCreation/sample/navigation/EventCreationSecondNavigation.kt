package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation.sample.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation.navigation.EventCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation.sample.EventCreationSecondScreen

fun NavController.navigateEventCreationSecond() {
    navigate(EventCreationRoute.SecondScreenRoute.route)
}

fun NavGraphBuilder.eventCreationSecondNavGraph() {
    composable(route = EventCreationRoute.SecondScreenRoute.route) {
        EventCreationSecondScreen()
    }
}
