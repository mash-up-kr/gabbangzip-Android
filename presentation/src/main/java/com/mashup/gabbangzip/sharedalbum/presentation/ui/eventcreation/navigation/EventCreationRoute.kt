package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.navigation

sealed interface EventCreationRoute {
    val route: String

    data object DetailScreenRoute : EventCreationRoute {
        override val route: String = "eventDetailScreenRoute"
    }

    companion object {
        val initRoute = DetailScreenRoute.route
    }
}
