package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.GroupDetailScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute.GroupDetailRoute.KEY_GROUP_ID

fun NavController.navigateGroupDetail(groupId: Long) {
    navigate("${MainRoute.GroupDetailRoute.route}/$groupId")
}

fun NavGraphBuilder.groupDetailNavGraph(
    sharedViewModel: MainViewModel,
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
    onClickOpenPhotoPickerButton: () -> Unit,
) {
    composable(
        route = "${MainRoute.GroupDetailRoute.route}/{$KEY_GROUP_ID}",
        arguments = listOf(
            navArgument(KEY_GROUP_ID) { type = NavType.LongType },
        ),
    ) { backStackEntry ->
        backStackEntry.arguments?.getLong(KEY_GROUP_ID)?.let {
            GroupDetailScreen(
                sharedViewModel = sharedViewModel,
                onClickGroupMemberButton = onClickGroupMemberButton,
                onClickBackButton = onClickBackButton,
                onClickOpenPhotoPickerButton = onClickOpenPhotoPickerButton,
            )
        }
    }
}
