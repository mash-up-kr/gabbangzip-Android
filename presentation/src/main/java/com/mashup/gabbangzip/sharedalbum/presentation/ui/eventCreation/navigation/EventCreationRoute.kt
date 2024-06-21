package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventCreation.navigation

sealed interface EventCreationRoute {
    val route: String

    data object FirstScreenRoute: EventCreationRoute {
        override val route: String = "eventFirstScreenRoute"
    }

    data object SecondScreenRoute: EventCreationRoute {
        override val route: String = "eventSecondScreenRoute"
    }

    companion object {
        val initRoute = FirstScreenRoute.route
    }
}
