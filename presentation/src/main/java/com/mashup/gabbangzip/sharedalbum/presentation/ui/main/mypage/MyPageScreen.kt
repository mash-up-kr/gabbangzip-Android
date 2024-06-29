package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage

import android.app.NotificationManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.GroupItemNormal
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.GroupTitle
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.LogoutDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.UserContainer
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.WithdrawalDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.viewmodel.MyPageViewModel

@Composable
fun MyPageScreen(
    onClickBack: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    navigateLoginAndFinish: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val versionName = remember {
        runCatching { context.packageManager.getPackageInfo(context.packageName, 0).versionName }
            .getOrDefault("")
    }
    // Todo : 갔다오면 isNotificationEnabled 가 변경되도록 구현
    val isNotificationEnabled = remember(configuration) {
        if ((context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.areNotificationsEnabled() == false) {
            "OFF"
        } else {
            "ON"
        }
    }

    when (state.dialogState) {
        MyPageUiState.DialogState.None -> Unit
        MyPageUiState.DialogState.Logout -> {
            LogoutDialog(
                onDismiss = {
                    viewModel.dismissDialog()
                },
                onConfirm = {
                    viewModel.dismissDialog()
                    viewModel.logout()
                    navigateLoginAndFinish()
                },
            )
        }

        MyPageUiState.DialogState.Withdrawal -> {
            WithdrawalDialog(
                onDismiss = {
                    viewModel.dismissDialog()
                },
                onConfirm = {
                    viewModel.dismissDialog()
                    viewModel.withdrawal(
                        onSuccess = {
                            navigateLoginAndFinish()
                        },
                    )
                },
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            leftIcon = TopBarIcon(
                size = 26.dp,
                leftPadding = 16.dp,
                description = stringResource(id = R.string.go_back),
                resId = R.drawable.ic_back,
                iconClickListener = onClickBack,
            ),
            titleText = stringResource(id = R.string.my_page),
            titleAlign = TopBarTitleAlign.CENTER,
            titleStyle = PicTypography.bodyMedium16,
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        )
        UserContainer(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 14.dp, horizontal = 16.dp),
            userName = state.userName,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(Gray20),
        )
        GroupTitle(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.notification_setting),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = onClickNotificationSetting)
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.app_notification_setting),
            subText = isNotificationEnabled,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(2.dp)
                .background(Gray20),
        )
        GroupTitle(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.account_setting),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.current_version),
            subText = versionName,
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = { viewModel.showLogoutDialog() })
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.logout),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = { viewModel.showWithdrawalDialog() })
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.account_withdrawal),
        )
    }
}
