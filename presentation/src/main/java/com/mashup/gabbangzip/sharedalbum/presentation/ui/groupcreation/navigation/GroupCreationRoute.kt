package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation

sealed interface GroupCreationRoute {
    val route: String

    data object IntroScreenRoute : GroupCreationRoute {
        override val route: String = "groupIntroScreenRoute"
    }

    data object NameScreenRoute : GroupCreationRoute {
        override val route: String = "groupNameScreenRoute"
    }

    data object KeywordScreenRoute : GroupCreationRoute {
        override val route: String = "groupKeywordScreenRoute"
    }

    companion object {
        val initRoute = IntroScreenRoute.route
    }
}
