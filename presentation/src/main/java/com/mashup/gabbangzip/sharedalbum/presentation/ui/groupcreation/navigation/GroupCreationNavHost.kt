package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.navigation.groupCreationIntroNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.navigation.groupCreationNameNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name.navigation.navigateToGroupCreationName

@Composable
fun GroupCreationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        groupCreationIntroNavGraph(
            onClickNextButton = { navController.navigateToGroupCreationName() },
        )
        groupCreationNameNavGraph(
            onBackButtonClicked = { navController.popBackStack() },
            onNextButtonClicked = {},
        )
    }
}
