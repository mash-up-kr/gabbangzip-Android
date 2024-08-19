package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.EventCreationState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.detail.navigation.eventCreationDetailNavGraph

@Composable
fun EventCreationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    eventCreationState: EventCreationState,
    clearEventCreationState: () -> Unit,
    onCompleteButtonClicked: (String) -> Unit,
    onGalleryButtonClicked: () -> Unit,
    onPictureDeleteButtonClicked: (Int) -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        eventCreationDetailNavGraph(
            eventCreationState = eventCreationState,
            onCompleteButtonClicked = onCompleteButtonClicked,
            onGalleryButtonClicked = onGalleryButtonClicked,
            onPictureDeleteButtonClicked = onPictureDeleteButtonClicked,
            onDismissButtonClicked = {
                clearEventCreationState()
                onBackButtonClicked()
            },
        )
    }
}
