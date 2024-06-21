package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.navigation

sealed interface GroupCreationRoute {
    val route: String

    data object FirstScreenRoute : GroupCreationRoute {
        override val route: String = "groupFirstScreenRoute"
    }

    data object SecondScreenRoute : GroupCreationRoute {
        override val route: String = "groupSecondScreenRoute"
    }

    companion object {
        val initRoute = FirstScreenRoute.route
    }
}
