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
            leftIconSize = 24.dp,
            leftIconLeftPadding = 16.dp,
            leftIconDescription = "뒤로가기",
            leftIconRes = R.drawable.ic_call_answer_low,
            titleIconLeftPadding = 8.dp,
            titleText = "PIC",
            titleAlign = TopBarTitleAlign.LEFT,
            rightIconSize = 24.dp,
            rightIconRightPadding = 10.dp,
            rightIconDescription = "내 정보",
            rightIconRes = R.drawable.ic_call_answer_video_low,
            rightIcon2Size = 24.dp,
            rightIcon2RightPadding = 16.dp,
            rightIcon2Description = "내 정보",
            rightIcon2Res = R.drawable.ic_call_answer_video,
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
            Button(onClick = { onClickEventMakeButton.invoke() }) {
                Text(text = "이벤트만들기 화면 가는 버튼")
            }
            Button(onClick = { onClickGroupDetailButton.invoke() }) {
                Text(text = "그룹상세 화면 가는 버튼")
            }
            Button(onClick = { onClickMyPageButton.invoke() }) {
                Text(text = "마이페이지 화면 가는 버튼")
            }
        }
    }
}
