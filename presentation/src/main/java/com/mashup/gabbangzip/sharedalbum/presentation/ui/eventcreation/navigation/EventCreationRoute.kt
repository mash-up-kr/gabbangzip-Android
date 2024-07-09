package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

sealed interface EventCreationRoute {
    val route: String

    data object IntroScreenRoute : EventCreationRoute {
        override val route: String = "eventIntroScreenRoute"
    }

    data object SecondScreenRoute : EventCreationRoute {
        override val route: String = "eventSecondScreenRoute"
    }

    companion object {
        val initRoute = IntroScreenRoute.route
    }
}
