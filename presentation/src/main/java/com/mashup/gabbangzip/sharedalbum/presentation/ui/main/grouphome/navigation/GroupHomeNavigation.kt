package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.GroupHomeScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateGroupHome() {
    navigate(MainRoute.GroupHomeRoute.route)
}

fun NavGraphBuilder.groupHomeNavGraph(
    navigateToGroupCreationAndFinish: () -> Unit,
    onClickGroupDetail: (id: Long) -> Unit,
    onClickEventMake: (Long) -> Unit,
    onClickMyPage: () -> Unit,
    onClickGroupMake: () -> Unit,
    onClickGroupEnter: () -> Unit,
) {
    composable(route = MainRoute.GroupHomeRoute.route) {
        GroupHomeScreen(
            navigateToGroupCreationAndFinish = navigateToGroupCreationAndFinish,
            onClickGroupDetail = onClickGroupDetail,
            onClickEventMake = onClickEventMake,
            onClickMyPage = onClickMyPage,
            onClickGroupMake = onClickGroupMake,
            onClickGroupEnter = onClickGroupEnter,
        )
    }
}
