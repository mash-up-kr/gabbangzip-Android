package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.sample.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.sample.GroupCreationFirstScreen

fun NavGraphBuilder.groupCreationFirstNavGraph(
    onClickNextButton: () -> Unit,
) {
    composable(route = GroupCreationRoute.FirstScreenRoute.route) {
        GroupCreationFirstScreen(
            onClickNextButton = { onClickNextButton.invoke() },
        )
    }
}
