package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography

@Composable
fun GroupItemNormal(
    modifier: Modifier = Modifier,
    text: String,
    stateText: String = "",
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = text,
            style = PicTypography.bodyMedium16,
            color = Gray80,
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = stateText,
            style = PicTypography.bodyMedium16,
            color = Gray60,
        )
    }
}
