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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarTitleAlign

@Composable
fun MyPageScreen(
    onClickBackButton: () -> Unit,
) {
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
                iconClickListener = onClickBackButton,
            ),
            titleText = stringResource(id = R.string.my_page),
            titleAlign = TopBarTitleAlign.CENTER,
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        )
        UserContainer(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            userName = "연규",
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
                .clickable { /** 알림설정 넘어가는 코드 **/ }
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
                .clickable { /** 로그아웃 코드 **/ }
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.logout),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { /** 회원탈퇴 코드 **/ }
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.withdrawal),
        )
    }
}
