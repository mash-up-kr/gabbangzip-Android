package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.GroupHomeScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateGroupHome() {
    navigate(MainRoute.GroupHomeRoute.route) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.groupHomeNavGraph(
    innerPadding: PaddingValues,
    navigateToGroupCreationAndFinish: () -> Unit,
    onClickGroupDetail: (id: Long) -> Unit,
    onClickEventMake: (Long) -> Unit,
    onClickMyPage: () -> Unit,
    onClickGroupMake: () -> Unit,
    onClickGroupEnter: () -> Unit,
    onClickSendFcmButton: (eventId: Long) -> Unit,
    onNavigateGallery: (eventId: Long) -> Unit,
    onNavigateVote: (eventId: Long) -> Unit,
    onShowSnackbar: (PicSnackbarType, String) -> Unit,
) {
    composable(route = MainRoute.GroupHomeRoute.route) {
        GroupHomeScreen(
            innerPadding = innerPadding,
            navigateToGroupCreationAndFinish = navigateToGroupCreationAndFinish,
            onClickGroupDetail = onClickGroupDetail,
            onClickEventMake = onClickEventMake,
            onClickMyPage = onClickMyPage,
            onClickGroupMake = onClickGroupMake,
            onClickGroupEnter = onClickGroupEnter,
            onClickSendFcmButton = onClickSendFcmButton,
            onNavigateGallery = onNavigateGallery,
            onNavigateVote = onNavigateVote,
            onShowSnackbar = onShowSnackbar,
        )
    }
}
