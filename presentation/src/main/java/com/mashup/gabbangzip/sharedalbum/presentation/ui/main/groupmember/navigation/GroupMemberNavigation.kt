package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.GroupMemberScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute.GroupDetailRoute.KEY_GROUP_ID
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute.GroupDetailRoute.KEY_GROUP_KEYWORD

fun NavController.navigateGroupMember(
    groupId: Long,
    keyword: String,
) {
    navigate("${MainRoute.GroupDetailRoute.route}/$groupId/$keyword")
}

fun NavGraphBuilder.groupMemberNavGraph(
    onClickBackButton: () -> Unit,
    onSnackbarRequired: (PicSnackbarType, String) -> Unit,
) {
    composable(
        route = "${MainRoute.GroupDetailRoute.route}/${KEY_GROUP_ID}/${KEY_GROUP_KEYWORD}",
        arguments = listOf(
            navArgument(KEY_GROUP_ID) { type = NavType.LongType },
            navArgument(KEY_GROUP_KEYWORD) { type = NavType.StringType },
        ),
    ) {
        GroupMemberScreen(
            onClickBackButton = onClickBackButton,
            onSnackbarRequired = onSnackbarRequired,
        )
    }
}
