package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation

import android.net.Uri
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.navigation.groupCreationIntroNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.navigation.groupCreationKeywordNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword.navigation.navigateToGroupCreationKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.navigation.groupCreationNameNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.navigation.navigateToGroupCreationName
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.navigation.groupCreationThumbnailNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail.navigation.navigateToGroupCreationThumbnail

@Composable
fun GroupCreationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    thumbnailUri: Uri?,
    onGetThumbnailButtonClicked: () -> Unit,
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
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = { navController.navigateToGroupCreationKeyword() },
        )
        groupCreationKeywordNavGraph(
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = { navController.navigateToGroupCreationThumbnail() },
        )
        groupCreationThumbnailNavGraph(
            thumbnailUri = thumbnailUri,
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = {},
            onGetThumbnailButtonClicked = onGetThumbnailButtonClicked,
        )
    }
}
