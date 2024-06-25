package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
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
fun MyPageScreen(
    onClickBackButton: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            leftIcon = TopBarIcon(
                size = 26.dp,
                leftPadding = 16.dp,
                description = "뒤로가기",
                resId = R.drawable.ic_call_answer_low,
                iconClickListener = onClickBackButton,
            ),
            titleText = "내 정보",
            titleAlign = TopBarTitleAlign.CENTER,
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .weight(1.0f),
            text = "마이페이지 화면입니다",
        )
    }
}
