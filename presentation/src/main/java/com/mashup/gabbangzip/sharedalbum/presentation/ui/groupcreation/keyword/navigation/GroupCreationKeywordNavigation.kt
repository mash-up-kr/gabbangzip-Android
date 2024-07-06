package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.GroupCreationKeywordScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute

fun NavController.navigateToGroupCreationKeyword() {
    navigate(GroupCreationRoute.KeywordScreenRoute.route)
}

fun NavGraphBuilder.groupCreationKeywordNavGraph(
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    composable(route = GroupCreationRoute.KeywordScreenRoute.route) {
        GroupCreationKeywordScreen(
            onBackButtonClicked = onBackButtonClicked,
            onNextButtonClicked = onNextButtonClicked,
        )
    }
}
