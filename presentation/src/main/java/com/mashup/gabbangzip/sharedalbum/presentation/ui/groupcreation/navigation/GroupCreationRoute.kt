package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation

sealed interface GroupCreationRoute {
    val route: String

    data object IntroScreenRoute : GroupCreationRoute {
        override val route: String = "groupIntroScreenRoute"
    }

    data object SecondScreenRoute : GroupCreationRoute {
        override val route: String = "groupSecondScreenRoute"
    }

    companion object {
        val initRoute = IntroScreenRoute.route
    }
}
