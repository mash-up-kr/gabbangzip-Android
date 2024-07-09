package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation

sealed interface MainRoute {
    val route: String

    data object GroupDetailRoute : MainRoute {
        override val route: String = "groupDetail"
    }

    data object GroupHomeRoute : MainRoute {
        override val route: String = "groupHome"
    }

    data object GroupMemberRoute : MainRoute {
        override val route: String = "groupMember"
    }

    data object MyPageRoute : MainRoute {
        override val route: String = "myPage"
    }

    companion object {
        val initRoute = MyPageRoute.route
    }
}
