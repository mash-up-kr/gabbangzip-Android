package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    titleText: String = "",
    titleAlign: TopBarTitleAlign = TopBarTitleAlign.CENTER,
    @DrawableRes leftIconRes: Int? = null,
    @DrawableRes rightIconRes: Int? = null,
    @DrawableRes rightIcon2Res: Int? = null,
    leftIconDescription: String? = null,
    rightIconDescription: String? = null,
    rightIcon2Description: String? = null,
    leftIconSize: Dp = 26.dp,
    rightIconSize: Dp = 26.dp,
    rightIcon2Size: Dp = 26.dp,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    leftIconLeftPadding: Dp = 0.dp,
    titleIconLeftPadding: Dp = 0.dp,
    rightIconRightPadding: Dp = 0.dp,
    rightIcon2RightPadding: Dp = 0.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = topPadding, bottom = bottomPadding),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = leftIconLeftPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leftIconRes != null) {
                Image(
                    modifier = Modifier.size(leftIconSize),
                    painter = painterResource(id = leftIconRes),
                    contentDescription = leftIconDescription,
                )
                VerticalSpacer(titleIconLeftPadding)
            }
            Text(
                modifier = Modifier.weight(1.0f),
                text = if (titleAlign == TopBarTitleAlign.LEFT) titleText else "",
            )
            if (rightIconRes != null) {
                Image(
                    modifier = Modifier.size(rightIconSize),
                    painter = painterResource(id = rightIconRes),
                    contentDescription = rightIconDescription,
                )
                VerticalSpacer(rightIconRightPadding)
            }
            if (rightIcon2Res != null) {
                Image(
                    modifier = Modifier.size(rightIcon2Size),
                    painter = painterResource(id = rightIcon2Res),
                    contentDescription = rightIcon2Description,
                )
                VerticalSpacer(rightIcon2RightPadding)
            }
        }
        if (titleAlign == TopBarTitleAlign.CENTER) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .wrapContentHeight(),
                text = titleText,
                textAlign = TextAlign.Center,
            )
        }
    }
}

enum class TopBarTitleAlign {
    CENTER, LEFT
}
