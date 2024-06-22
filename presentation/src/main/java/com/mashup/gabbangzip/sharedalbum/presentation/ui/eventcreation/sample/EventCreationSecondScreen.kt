package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.sample

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
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarTitleAlign

@Composable
fun EventCreationSecondScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            leftIconSize = 26.dp,
            leftIconLeftPadding = 16.dp,
            leftIconDescription = "뒤로가기",
            leftIconRes = R.drawable.ic_call_answer_low,
            titleText = "이벤트 만들기",
            titleAlign = TopBarTitleAlign.CENTER,
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .weight(1.0f),
            text = "이벤트만들기 두번째 화면입니다",
        )
    }
}
