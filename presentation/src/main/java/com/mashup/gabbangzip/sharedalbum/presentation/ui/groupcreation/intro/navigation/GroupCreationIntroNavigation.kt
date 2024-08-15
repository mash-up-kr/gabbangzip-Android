package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.GroupCreationIntroScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute

fun NavGraphBuilder.groupCreationIntroNavGraph(
    onClickEnterByCodeButton: () -> Unit,
    onClickNextButton: () -> Unit,
) {
    composable(route = GroupCreationRoute.IntroScreenRoute.route) {
        GroupCreationIntroScreen(
            onClickEnterByCodeButton = onClickEnterByCodeButton,
            onClickNextButton = onClickNextButton,
        )
    }
}
