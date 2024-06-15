package com.mashup.gabbangzip.sharedalbum.presentation.common

sealed interface Route {
    val route: String
}

data object GroupMakeRoute : Route {
    override val route: String = "groupMake"
}

data object HomeRoute : Route {
    override val route: String = "home"
}

data object LoginRoute : Route {
    override val route: String = "login"
}
