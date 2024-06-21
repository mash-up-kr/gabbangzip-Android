package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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
