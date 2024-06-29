package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.navigation.groupDetailNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.navigation.navigateGroupDetail
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.navigation.groupMemberNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.navigation.navigateGroupMember
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouplist.navigation.groupListNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation.myPageNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation.navigateMyPage

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    onClickEventMakeButton: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    navigateLoginAndFinish: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        groupListNavGraph(
            onClickGroupDetail = { navController.navigateGroupDetail() },
            onClickMyPage = { navController.navigateMyPage() },
            onClickEventMake = { onClickEventMakeButton() },
        )
        groupDetailNavGraph(
            onClickGroupMemberButton = { navController.navigateGroupMember() },
            onClickBackButton = { navController.popBackStack() },
        )
        groupMemberNavGraph(
            onClickBackButton = { navController.popBackStack() },
        )
        myPageNavGraph(
            onClickBack = { navController.popBackStack() },
            onClickNotificationSetting = onClickNotificationSetting,
            navigateLoginAndFinish = navigateLoginAndFinish,
        )
    }
}
