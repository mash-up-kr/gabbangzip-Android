package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

sealed interface EventCreationRoute {
    val route: String

    data object FirstScreenRoute : EventCreationRoute {
        override val route: String = "eventFirstScreenRoute"
    }

    data object SecondScreenRoute : EventCreationRoute {
        override val route: String = "eventSecondScreenRoute"
    }

    companion object {
        val initRoute = FirstScreenRoute.route
    }
}
