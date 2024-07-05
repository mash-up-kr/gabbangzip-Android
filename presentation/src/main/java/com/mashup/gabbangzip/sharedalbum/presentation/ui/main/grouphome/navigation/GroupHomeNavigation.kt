package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.GroupHomeScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupHomeUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateGroupHome() {
    navigate(MainRoute.GroupListRoute.route)
}

fun NavGraphBuilder.groupHomeNavGraph(
    onClickGroupDetail: (id: Long) -> Unit,
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
