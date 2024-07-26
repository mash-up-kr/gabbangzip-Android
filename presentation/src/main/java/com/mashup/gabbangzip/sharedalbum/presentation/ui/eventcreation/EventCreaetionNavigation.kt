package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute

fun NavController.navigateToEventCreation() {
    navigate(EventCreationRoute.EventCreationScreenRoute.route)
}

fun NavGraphBuilder.eventCreationNavGraph(
    date: String,
    pictures: List<Uri?>,
    onCompleteButtonClicked: (String) -> Unit,
    onGalleryButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    composable(route = EventCreationRoute.EventCreationScreenRoute.route) {
        EventCreationScreen(
            date = date,
            pictures = pictures,
            onCompleteButtonClicked = onCompleteButtonClicked,
            onGalleryButtonClicked = onGalleryButtonClicked,
            onBackButtonClicked = onBackButtonClicked,
        )
    }
}
