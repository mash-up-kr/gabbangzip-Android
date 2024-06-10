package com.mashup.gabbangzip.sharedalbum.presentation.common

sealed interface BaseRoute {
    val route: String
}

data object LoginRoute: BaseRoute {
    override val route: String = "login"
}

data object HomeRoute: BaseRoute {
    override val route: String = "home"
}

data object GroupMakeRoute: BaseRoute {
    override val route: String = "groupMake"
}
