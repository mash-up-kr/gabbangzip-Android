package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.GroupItemNormal
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.GroupItemVersion
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.GroupTitle
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.MyPageDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.UserContainer
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.viewmodel.MyPageViewModel

@Composable
fun MyPageScreen(
    userName: String,
    onClickBack: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    navigateLoginAndFinish: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    var showDialog by remember { mutableStateOf(DialogState.None) }

    if (showDialog == DialogState.Logout) {
        MyPageDialog(
            titleText = stringResource(id = R.string.dialog_logout_title),
            contentsText = "",
            confirmText = stringResource(id = R.string.logout),
            dismissText = stringResource(id = R.string.cancel),
            onDismiss = { showDialog = DialogState.None },
            onConfirm = {
                showDialog = DialogState.None
                viewModel.logout()
                navigateLoginAndFinish()
            },
        )
    } else if (showDialog == DialogState.Withdrawal) {
        MyPageDialog(
            titleText = stringResource(id = R.string.dialog_withdrawal_title),
            contentsText = stringResource(id = R.string.dialog_withdrawal_contents),
            confirmText = stringResource(id = R.string.withdrawal),
            dismissText = stringResource(id = R.string.cancel),
            onDismiss = { showDialog = DialogState.None },
            onConfirm = {
                showDialog = DialogState.None
                viewModel.withdrawal()
            },
        )
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
                .padding(vertical = 20.dp, horizontal = 16.dp),
            userName = userName,
            loginWay = stringResource(id = R.string.login_kakao),
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
        GroupItemVersion(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 21.dp),
            titleText = stringResource(id = R.string.current_version),
            versionName = "1.0.0",
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = { showDialog = DialogState.Logout })
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.logout),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = { showDialog = DialogState.Withdrawal })
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.account_withdrawal),
        )
    }
}

enum class DialogState {
    None, Logout, Withdrawal
}
