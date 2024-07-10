package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute

fun NavController.navigateToEventCreation() {
    navigate(EventCreationRoute.EventCreationScreenRoute.route)
}

fun NavGraphBuilder.eventCreationNavGraph(
    onCompleteButtonClicked: () -> Unit,
    onGalleryButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    composable(route = EventCreationRoute.EventCreationScreenRoute.route) {
        EventCreationScreen(
            onCompleteButtonClicked = onCompleteButtonClicked,
            onGalleryButtonClicked = onGalleryButtonClicked,
            onBackButtonClicked = onBackButtonClicked,
        )
    }
}
