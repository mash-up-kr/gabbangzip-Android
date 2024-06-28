package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography

@Composable
fun UserContainer(
    modifier: Modifier = Modifier,
    userName: String,
) {
    Text(
        modifier = modifier,
        text = userName,
        style = PicTypography.headBold20,
        color = Gray80,
    )
}
