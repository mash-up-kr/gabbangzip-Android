package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.EventCreationState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.detail.EventCreationDetailScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute

fun NavController.navigateToEventCreationDetail() {
    navigate(EventCreationRoute.DetailScreenRoute.route)
}

fun NavGraphBuilder.eventCreationDetailNavGraph(
    eventCreationState: EventCreationState,
    onCompleteButtonClicked: (String) -> Unit,
    onGalleryButtonClicked: () -> Unit,
    onPictureDeleteButtonClicked: (Int) -> Unit,
    onDismissButtonClicked: () -> Unit,
) {
    composable(route = EventCreationRoute.DetailScreenRoute.route) {
        EventCreationDetailScreen(
            state = eventCreationState,
            onCompleteButtonClicked = onCompleteButtonClicked,
            onGalleryButtonClicked = onGalleryButtonClicked,
            onPictureDeleteButtonClicked = onPictureDeleteButtonClicked,
            onDismissButtonClicked = onDismissButtonClicked,
        )
    }
}
