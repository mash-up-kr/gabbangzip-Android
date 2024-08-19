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

    data object ThumbnailScreenRoute : GroupCreationRoute {
        override val route: String = "groupThumbnailScreenRoute"
    }

    data object CompleteScreenRoute : GroupCreationRoute {
        override val route: String = "groupCompleteScreenRoute"
    }

    companion object {
        val initRoute = IntroScreenRoute.route
    }
}
