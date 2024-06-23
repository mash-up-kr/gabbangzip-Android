package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupMember.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupMember.GroupMemberScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateGroupMember() {
    navigate(MainRoute.GroupMemberRoute.route)
}

fun NavGraphBuilder.groupMemberNavGraph(onClickBackButton: () -> Unit) {
    composable(route = MainRoute.GroupMemberRoute.route) {
        GroupMemberScreen(onClickBackButton)
    }
}
