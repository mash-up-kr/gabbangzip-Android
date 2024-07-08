package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.GroupDetailScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

private const val KEY_GROUP_ID = "groupId"

fun NavController.navigateGroupDetail(groupId: Long) {
    navigate("${MainRoute.GroupDetailRoute.route}/$groupId")
}

fun NavGraphBuilder.groupDetailNavGraph(
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    composable(
        route = "${MainRoute.GroupDetailRoute.route}/{$KEY_GROUP_ID}",
        arguments = listOf(
            navArgument(KEY_GROUP_ID) { type = NavType.LongType },
        ),
    ) { backStackEntry ->
        backStackEntry.arguments?.getLong(KEY_GROUP_ID)?.let { groupId ->
            GroupDetailScreen(
                groupId = groupId,
                onClickGroupMemberButton = onClickGroupMemberButton,
                onClickBackButton = onClickBackButton,
            )
        }
    }
}
