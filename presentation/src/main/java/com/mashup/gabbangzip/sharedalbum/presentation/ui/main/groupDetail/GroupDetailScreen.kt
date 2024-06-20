package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail

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
fun GroupDetailScreen(
    onClickGroupMemberButton: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
