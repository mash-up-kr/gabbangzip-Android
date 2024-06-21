package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.sample.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupCreation.sample.GroupCreationFirstScreen

fun NavGraphBuilder.groupCreationFirstNavGraph(
    onClickNextButton: () -> Unit,
) {
    composable(route = GroupCreationRoute.FirstScreenRoute.route) {
        GroupCreationFirstScreen(
            onClickNextButton = { onClickNextButton.invoke() },
        )
    }
}
