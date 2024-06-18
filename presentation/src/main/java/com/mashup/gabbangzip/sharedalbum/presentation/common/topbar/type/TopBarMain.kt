package com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TopBarMain(
    modifier: Modifier = Modifier,
    titleText: String,
    @DrawableRes iconRes: Int,
    onClickTitle: () -> Unit,
) {
    Row(modifier = modifier.clickable { onClickTitle() }) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = iconRes),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = titleText,
            textAlign = TextAlign.Left,
        )
    }
}
