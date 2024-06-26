package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.start.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.start.GroupCreationFirstScreen

fun NavGraphBuilder.groupCreationStartNavGraph(
    onClickNextButton: () -> Unit,
) {
    composable(route = GroupCreationRoute.FirstScreenRoute.route) {
        GroupCreationFirstScreen(
            onClickNextButton = onClickNextButton,
        )
    }
}
