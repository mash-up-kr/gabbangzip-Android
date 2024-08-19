package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.GroupCreationKeywordScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation.GroupCreationRoute
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

fun NavController.navigateToGroupCreationKeyword() {
    navigate(GroupCreationRoute.KeywordScreenRoute.route)
}

fun NavGraphBuilder.groupCreationKeywordNavGraph(
    initialKeyword: GroupKeyword,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: (keyword: GroupKeyword) -> Unit,
) {
    composable(route = GroupCreationRoute.KeywordScreenRoute.route) {
        GroupCreationKeywordScreen(
            initialKeyword = initialKeyword,
            onBackButtonClicked = onBackButtonClicked,
            onNextButtonClicked = onNextButtonClicked,
        )
    }
}
