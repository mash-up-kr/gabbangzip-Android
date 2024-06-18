package com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TopBarTitle(
    modifier: Modifier = Modifier,
    titleText: String,
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = titleText,
            textAlign = TextAlign.Center,
        )
    }
}
