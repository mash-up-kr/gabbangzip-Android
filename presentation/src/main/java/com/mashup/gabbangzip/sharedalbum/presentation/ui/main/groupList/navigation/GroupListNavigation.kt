package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupList.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupList.GroupListScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateGroupList() {
    navigate(MainRoute.GroupListRoute.route)
}

fun NavGraphBuilder.groupListNavGraph(
    onClickGroupDetailButton: () -> Unit,
    onClickEventMakeButton: () -> Unit,
    onClickMyPageButton: () -> Unit,
) {
    composable(route = MainRoute.GroupListRoute.route) {
        GroupListScreen(
            onClickGroupDetailButton = { onClickGroupDetailButton.invoke() },
            onClickEventMakeButton = { onClickEventMakeButton.invoke() },
            onClickMyPageButton = { onClickMyPageButton.invoke() }
        )
    }
}
