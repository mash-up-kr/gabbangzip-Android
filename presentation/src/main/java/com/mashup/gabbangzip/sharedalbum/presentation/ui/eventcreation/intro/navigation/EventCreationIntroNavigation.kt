package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.intro.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.intro.EventCreationIntroScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute

fun NavGraphBuilder.eventCreationIntroNavGraph(
    onNextButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    composable(route = EventCreationRoute.IntroScreenRoute.route) {
        EventCreationIntroScreen(
            onNextButtonClicked = onNextButtonClicked,
            onBackButtonClicked = onBackButtonClicked,
        )
    }
}
