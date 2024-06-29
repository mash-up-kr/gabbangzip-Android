package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouplist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouplist.GroupHomeScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouplist.model.GroupHomeUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateGroupList() {
    navigate(MainRoute.GroupListRoute.route)
}

fun NavGraphBuilder.groupListNavGraph(
    onClickGroupDetail: (id: Int) -> Unit,
    onClickEventMake: () -> Unit,
    onClickMyPage: () -> Unit,
) {
    composable(route = MainRoute.GroupListRoute.route) {
        GroupHomeScreen(
            state = GroupHomeUiState(),
            onClickGroupDetail = onClickGroupDetail,
            onClickEventMake = onClickEventMake,
            onClickMyPage = onClickMyPage,
        )
    }
}
