package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.GroupCreationState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.navigation.groupCreationCompleteNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.navigation.navigateToGroupCreationComplete
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.navigation.groupCreationIntroNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.navigation.groupCreationKeywordNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.navigation.navigateToGroupCreationKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.navigation.groupCreationNameNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.navigation.navigateToGroupCreationName
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.navigation.groupCreationThumbnailNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.navigation.navigateToGroupCreationThumbnail
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

@Composable
fun GroupCreationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    groupCreationState: GroupCreationState,
    onGetThumbnailButtonClicked: () -> Unit,
    updateName: (name: String) -> Unit,
    updateKeyword: (keyword: GroupKeyword) -> Unit,
    onNextButtonClicked: () -> Unit,
    showSnackbarMessage: (message: String) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        groupCreationIntroNavGraph(
            onClickNextButton = { navController.navigateToGroupCreationName() },
        )
        groupCreationNameNavGraph(
            initialName = groupCreationState.name,
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = { name ->
                navController.navigateToGroupCreationKeyword()
                updateName(name)
            },
        )
        groupCreationKeywordNavGraph(
            initialKeyword = groupCreationState.keyword,
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = { keyword ->
                navController.navigateToGroupCreationThumbnail()
                updateKeyword(keyword)
            },
        )
        groupCreationThumbnailNavGraph(
            initialThumbnail = groupCreationState.thumbnail,
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = { navController.navigateToGroupCreationComplete() },
            onGetThumbnailButtonClicked = onGetThumbnailButtonClicked,
        )
        groupCreationCompleteNavGraph(
            onNextButtonClicked = onNextButtonClicked,
            showSnackbarMessage = showSnackbarMessage,
        )
    }
}
