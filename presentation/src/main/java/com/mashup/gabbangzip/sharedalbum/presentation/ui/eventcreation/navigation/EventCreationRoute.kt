package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

sealed interface EventCreationRoute {
    val route: String

    data object IntroScreenRoute : EventCreationRoute {
        override val route: String = "eventIntroScreenRoute"
    }

    data object DetailScreenRoute : EventCreationRoute {
        override val route: String = "eventDetailScreenRoute"
    }

    companion object {
        val initRoute = IntroScreenRoute.route
    }
}
