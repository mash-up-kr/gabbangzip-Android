package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.navigation.groupDetailNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.navigation.navigateGroupDetail
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupList.navigation.groupListNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupMember.navigation.groupMemberNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupMember.navigation.navigateGroupMember
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation.myPageNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation.navigateMyPage

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    onClickEventMakeButton: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        groupListNavGraph(
            onClickEventMakeButton = { onClickEventMakeButton.invoke() },
            onClickGroupDetailButton = { navController.navigateGroupDetail() },
            onClickMyPageButton = { navController.navigateMyPage() },
        )
        groupDetailNavGraph(
            onClickGroupMemberButton = { navController.navigateGroupMember() },
        )
        groupMemberNavGraph()
        myPageNavGraph()
    }
}
