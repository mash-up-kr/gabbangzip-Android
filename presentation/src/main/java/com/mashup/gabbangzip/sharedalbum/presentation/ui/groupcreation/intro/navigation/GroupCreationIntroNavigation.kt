package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.GroupCreationIntroScreen

fun NavGraphBuilder.groupCreationIntroNavGraph(
    onClickNextButton: () -> Unit,
) {
    composable(route = GroupCreationRoute.FirstScreenRoute.route) {
        GroupCreationIntroScreen(
            onClickNextButton = onClickNextButton,
        )
    }
}
