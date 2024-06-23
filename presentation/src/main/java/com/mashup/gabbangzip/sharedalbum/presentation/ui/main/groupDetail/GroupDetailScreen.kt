package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail

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
fun GroupDetailScreen(
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            titleText = "뛰뛰빵빵 가빵집",
            titleAlign = TopBarTitleAlign.LEFT,
            topPadding = 16.dp,
            bottomPadding = 16.dp,
            leftIcon = TopBarIcon(
                size = 26.dp,
                leftPadding = 8.dp,
                description = "뒤로가기",
                resId = R.drawable.ic_call_answer_low,
                rightPadding = 4.dp,
                iconClickListener = { onClickBackButton.invoke() }
            ),
            rightIcon1 = TopBarIcon(
                size = 30.dp,
                rightPadding = 32.dp,
                description = "내 정보",
                resId = R.drawable.ic_call_answer_video_low,
                iconClickListener = {}
            ),
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
                text = "그룹 상세 화면입니다",
            )
            Button(onClick = { onClickGroupMemberButton.invoke() }) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "그룹 멤버 화면 가는 버튼",
                )
            }
        }
    }
}
