package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.navigation

interface VoteNavRoute {
    val route: String

    data object VoteRoute : VoteNavRoute {
        override val route: String = "voteRoute"
    }

    data object VoteCompleteRoute : VoteNavRoute {
        override val route: String = "voteCompleteRoute"
    }

    companion object {
        val initRoute = VoteRoute.route
    }
}
