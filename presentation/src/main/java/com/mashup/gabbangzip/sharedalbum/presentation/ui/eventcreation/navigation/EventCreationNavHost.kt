package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.EventCreationState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.eventCreationNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.intro.navigation.eventCreationIntroNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigateToEventCreation

@Composable
fun EventCreationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    eventCreationState: EventCreationState,
    onCompleteButtonClicked: (String) -> Unit,
    onGalleryButtonClicked: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        eventCreationIntroNavGraph(
            onNextButtonClicked = navController::navigateToEventCreation,
            onBackButtonClicked = {},
        )
        eventCreationNavGraph(
            pictures = eventCreationState.pictures,
            onCompleteButtonClicked = onCompleteButtonClicked,
            onGalleryButtonClicked = onGalleryButtonClicked,
            onBackButtonClicked = navController::popBackStack,
        )
    }
}
