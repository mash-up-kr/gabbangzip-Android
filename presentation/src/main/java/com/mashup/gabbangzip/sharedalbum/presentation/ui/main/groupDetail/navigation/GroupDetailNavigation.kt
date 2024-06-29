package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.GroupDetailScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateGroupDetail() {
    // TODO : 디테일 화면 구현 하시는 분 id를 넘겨주시면 됩니다.
    navigate(MainRoute.GroupDetailRoute.route)
}

fun NavGraphBuilder.groupDetailNavGraph(
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    composable(route = MainRoute.GroupDetailRoute.route) {
        GroupDetailScreen(
            onClickGroupMemberButton = onClickGroupMemberButton,
            onClickBackButton = onClickBackButton,
        )
    }
}
