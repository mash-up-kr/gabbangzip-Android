package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(leftPadding: Dp = 0.dp) {
    Spacer(
        modifier = Modifier
            .width(leftPadding)
            .wrapContentHeight(),
    )
}
