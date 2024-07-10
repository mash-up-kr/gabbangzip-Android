package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

sealed interface EventCreationRoute {
    val route: String

    data object IntroScreenRoute : EventCreationRoute {
        override val route: String = "eventIntroScreenRoute"
    }

    data object EventCreationScreenRoute : EventCreationRoute {
        override val route: String = "eventCreationScreenRoute"
    }

    companion object {
        val initRoute = IntroScreenRoute.route
    }
}
