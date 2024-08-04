package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.navigation.groupDetailNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.navigation.navigateGroupDetail
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.navigation.groupHomeNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.navigation.groupMemberNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.navigation.navigateGroupMember
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation.myPageNavGraph
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.navigation.navigateMyPage

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    sharedViewModel: MainViewModel,
    navController: NavHostController,
    startDestination: String,
    onClickEventMakeButton: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    navigateLoginAndFinish: () -> Unit,
    onClickOpenPhotoPickerButton: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        groupHomeNavGraph(
            onClickGroupDetail = { id -> navController.navigateGroupDetail(id) },
            onClickMyPage = { navController.navigateMyPage() },
            onClickEventMake = { onClickEventMakeButton() },
            onClickGroupMake = { /* TODO : 그룹 생성 화면으로 이동 */ },
            onClickGroupEnter = { /* TODO : 초대 코드 입력 화면으로 이동 */ },
        )
        groupDetailNavGraph(
            sharedViewModel = sharedViewModel,
            onClickGroupMemberButton = { navController.navigateGroupMember() },
            onClickBackButton = { navController.popBackStack() },
            onClickOpenPhotoPickerButton = onClickOpenPhotoPickerButton,
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
