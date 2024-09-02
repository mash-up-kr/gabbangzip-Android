package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.MyPageScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateMyPage() {
    navigate(MainRoute.MyPageRoute.route)
}

fun NavGraphBuilder.myPageNavGraph(
    innerPadding: PaddingValues,
    onClickBack: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    navigateLoginAndFinish: () -> Unit,
) {
    composable(route = MainRoute.MyPageRoute.route) {
        MyPageScreen(
            innerPadding = innerPadding,
            onClickBack = onClickBack,
            onClickNotificationSetting = onClickNotificationSetting,
            navigateLoginAndFinish = navigateLoginAndFinish,
        )
    }
}
