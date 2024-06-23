package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.sample.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation.EventCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.sample.EventCreationFirstScreen

fun NavGraphBuilder.eventCreationFirstNavGraph(
    onClickNextButton: () -> Unit,
) {
    composable(route = EventCreationRoute.FirstScreenRoute.route) {
        EventCreationFirstScreen(
            onClickNextButton = onClickNextButton,
        )
    }
}
