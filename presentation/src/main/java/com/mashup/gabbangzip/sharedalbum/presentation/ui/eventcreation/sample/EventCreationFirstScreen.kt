package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.sample

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
fun EventCreationFirstScreen(
    onClickNextButton: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "이벤트만들기 첫번째 화면입니다",
        )
        Button(onClick = { onClickNextButton.invoke() }) {
            Text(text = "다음 화면으로 이동하는 버튼")
        }
    }
}
