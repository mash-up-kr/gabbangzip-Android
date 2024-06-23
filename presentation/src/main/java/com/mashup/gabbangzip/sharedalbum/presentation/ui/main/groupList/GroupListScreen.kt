package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarTitleAlign

@Composable
fun GroupListScreen(
    onClickGroupDetailButton: () -> Unit,
    onClickEventMakeButton: () -> Unit,
    onClickMyPageButton: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            leftIcon = TopBarIcon(
                size = 24.dp,
                leftPadding = 16.dp,
                description = "",
                resId = R.drawable.ic_call_answer_low,
                rightPadding = 8.dp,
                iconClickListener = {}
            ),
            titleText = "PIC",
            titleAlign = TopBarTitleAlign.LEFT,
            rightIcon1 = TopBarIcon(
                size = 24.dp,
                rightPadding = 10.dp,
                description = "알림",
                resId = R.drawable.ic_call_answer_video_low,
                iconClickListener = {}
            ),
            rightIcon2 = TopBarIcon(
                size = 24.dp,
                rightPadding = 16.dp,
                description = "내 정보",
                resId = R.drawable.ic_call_answer_video,
                iconClickListener = onClickMyPageButton
            ),
            topPadding = 16.dp,
            bottomPadding = 16.dp,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "메인 화면입니다",
            )
            Button(onClick = onClickEventMakeButton) {
                Text(text = "이벤트만들기 화면 가는 버튼")
            }
            Button(onClick = onClickGroupDetailButton) {
                Text(text = "그룹상세 화면 가는 버튼")
            }
        }
    }
}
