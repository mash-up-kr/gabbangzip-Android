package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.navigation

import android.graphics.Bitmap
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.GroupDetailScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.HistoryDetailState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute.GroupDetailRoute.KEY_GROUP_ID

fun NavController.navigateGroupDetail(groupId: Long) {
    navigate("${MainRoute.GroupDetailRoute.route}/$groupId")
}

fun NavGraphBuilder.groupDetailNavGraph(
    onClickGroupMemberButton: (groupId: Long, keyword: String) -> Unit,
    onClickBackButton: () -> Unit,
    onClickOpenPhotoPickerButton: (eventId: Long) -> Unit,
    onClickSendFcmButton: (eventId: Long) -> Unit,
    onClickVoteButton: (eventId: Long) -> Unit,
    onClickEventMake: (Long) -> Unit,
    onClickShareButton: (Bitmap) -> Unit,
    onClickHistoryItem: (HistoryDetailState) -> Unit,
    onErrorEvent: () -> Unit,
) {
    composable(
        route = "${MainRoute.GroupDetailRoute.route}/{$KEY_GROUP_ID}",
        arguments = listOf(
            navArgument(KEY_GROUP_ID) { type = NavType.LongType },
        ),
    ) { backStackEntry ->
        backStackEntry.arguments?.getLong(KEY_GROUP_ID)?.let { groupId ->
            GroupDetailScreen(
                onClickGroupMemberButton = { keyword ->
                    onClickGroupMemberButton(groupId, keyword.name)
                },
                onClickBackButton = onClickBackButton,
                onClickOpenPhotoPickerButton = onClickOpenPhotoPickerButton,
                onClickSendFcmButton = onClickSendFcmButton,
                onClickVoteButton = onClickVoteButton,
                onClickShareButton = onClickShareButton,
                onClickHistoryItem = onClickHistoryItem,
                onClickEventMake = { onClickEventMake(groupId) },
                onErrorEvent = onErrorEvent,
            )
        }
    }
}
