package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.GroupCreationUiState
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
    groupCreationUiState: GroupCreationUiState,
    onGetThumbnailButtonClicked: () -> Unit,
    updateName: (name: String) -> Unit,
    updateKeyword: (keyword: GroupKeyword) -> Unit,
    createGroup: () -> Unit,
    finishGroupCreation: () -> Unit,
    showSnackBarMessage: (type: PicSnackbarType, message: String) -> Unit,
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
            initialName = groupCreationUiState.name,
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = { name ->
                navController.navigateToGroupCreationKeyword()
                updateName(name)
            },
        )
        groupCreationKeywordNavGraph(
            initialKeyword = groupCreationUiState.keyword,
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = { keyword ->
                navController.navigateToGroupCreationThumbnail()
                updateKeyword(keyword)
            },
        )
        groupCreationThumbnailNavGraph(
            state = groupCreationUiState,
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = createGroup,
            onGetThumbnailButtonClicked = onGetThumbnailButtonClicked,
            navigateNextScreen = { navController.navigateToGroupCreationComplete() },
        )
        groupCreationCompleteNavGraph(
            groupCreationResult = groupCreationUiState.groupCreationResult,
            onNextButtonClicked = finishGroupCreation,
            showSnackBarMessage = showSnackBarMessage,
        )
    }
}
