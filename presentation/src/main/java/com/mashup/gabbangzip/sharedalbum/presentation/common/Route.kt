package com.mashup.gabbangzip.sharedalbum.presentation.common

import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.TopBarState

sealed interface Route {
    companion object {
        val initRoute = LoginRoute.route

        fun getRoute(route: String) = when (route) {
            GroupMakeRoute.route -> GroupMakeRoute
            HomeRoute.route -> HomeRoute
            LoginRoute.route -> LoginRoute
            else -> null
        }
    }

    val route: String
    val topBarState: TopBarState
}

data object GroupMakeRoute : Route {
    override val route: String = "groupMake"
    override val topBarState: TopBarState = TopBarState.Progress(
        titleText = "그룹 만들기",
        max = 3,
        progress = 1,
    )
}

data object HomeRoute : Route {
    override val route: String = "home"
    override val topBarState: TopBarState = TopBarState.Main(
        titleText = "홈 화면",
        iconRes = R.drawable.ic_launcher_foreground,
    )
}

data object LoginRoute : Route {
    override val route: String = "login"
    override val topBarState: TopBarState = TopBarState.None
}
