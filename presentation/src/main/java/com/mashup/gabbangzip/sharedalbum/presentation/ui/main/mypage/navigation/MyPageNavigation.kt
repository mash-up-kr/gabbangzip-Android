package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.MyPageScreen
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation.MainRoute

fun NavController.navigateMyPage() {
    navigate(MainRoute.MyPageRoute.route)
}

fun NavGraphBuilder.myPageNavGraph(
    onClickBack: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    navigateLoginAndFinish: () -> Unit,
) {
    composable(route = MainRoute.MyPageRoute.route) {
        MyPageScreen(
            onClickBack = onClickBack,
            onClickNotificationSetting = onClickNotificationSetting,
            navigateLoginAndFinish = navigateLoginAndFinish,
        )
    }
}
