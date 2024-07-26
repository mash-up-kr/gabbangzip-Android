package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute

fun NavController.navigateToEventCreation() {
    navigate(EventCreationRoute.EventCreationScreenRoute.route)
}

fun NavGraphBuilder.eventCreationNavGraph(
    eventCreationState: EventCreationState,
    updateDialogState: (Boolean) -> Unit,
    onCompleteButtonClicked: (String) -> Unit,
    onGalleryButtonClicked: () -> Unit,
    onDismissButtonClicked: () -> Unit,
) {
    composable(route = EventCreationRoute.EventCreationScreenRoute.route) {
        EventCreationScreen(
            state = eventCreationState,
            updateDialogState = updateDialogState,
            onCompleteButtonClicked = onCompleteButtonClicked,
            onGalleryButtonClicked = onGalleryButtonClicked,
            onDismissButtonClicked = onDismissButtonClicked,
        )
    }
}
