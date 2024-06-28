package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography

@Composable
fun GroupTitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = PicTypography.headBold14,
        color = Gray80,
    )
}

@Preview(showBackground = true)
@Composable
fun GroupTitlePreview() {
    GroupTitle(text = "알림 설정")
}
