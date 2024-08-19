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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rippleClickable

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    titleText: String = "",
    titleAlign: TopBarTitleAlign = TopBarTitleAlign.CENTER,
    titleStyle: TextStyle = TextStyle.Default,
    leftIcon: TopBarIcon? = null,
    rightIcon1: TopBarIcon? = null,
    rightIcon2: TopBarIcon? = null,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = topPadding, bottom = bottomPadding),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leftIcon?.resId != null) {
                Image(
                    modifier = Modifier
                        .rippleClickable { leftIcon.iconClickListener() }
                        .padding(start = leftIcon.leftPadding, end = leftIcon.rightPadding)
                        .size(leftIcon.size),
                    painter = painterResource(id = leftIcon.resId),
                    contentDescription = leftIcon.description,
                )
            }
            Text(
                modifier = Modifier.weight(1.0f),
                text = if (titleAlign == TopBarTitleAlign.LEFT) titleText else "",
                style = titleStyle,
            )
            if (rightIcon1?.resId != null) {
                Image(
                    modifier = Modifier
                        .rippleClickable { rightIcon1.iconClickListener() }
                        .padding(start = rightIcon1.leftPadding, end = rightIcon1.rightPadding)
                        .size(rightIcon1.size),
                    painter = painterResource(id = rightIcon1.resId),
                    contentDescription = rightIcon1.description,
                )
            }
            if (rightIcon2?.resId != null) {
                Image(
                    modifier = Modifier
                        .rippleClickable { rightIcon2.iconClickListener() }
                        .padding(start = rightIcon2.leftPadding, end = rightIcon2.rightPadding)
                        .size(rightIcon2.size),
                    painter = painterResource(id = rightIcon2.resId),
                    contentDescription = rightIcon2.description,
                )
            }
        }
        if (titleAlign == TopBarTitleAlign.CENTER) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .wrapContentHeight(),
                text = titleText,
                textAlign = TextAlign.Center,
                style = titleStyle,
            )
        }
    }
}

enum class TopBarTitleAlign {
    CENTER, LEFT
}

data class TopBarIcon(
    @DrawableRes val resId: Int,
    val description: String,
    val size: Dp,
    val leftPadding: Dp = 0.dp,
    val rightPadding: Dp = 0.dp,
    val iconClickListener: () -> Unit,
)

class TopBarProvider : PreviewParameterProvider<TopBarState> {
    override val values: Sequence<TopBarState> = sequenceOf(
        TopBarState(
            title = "제목 가운데",
            textAlign = TopBarTitleAlign.CENTER,
        ),
        TopBarState(
            title = "왼쪽 아이콘이 있는 가운데 제목",
            textAlign = TopBarTitleAlign.CENTER,
            leftIcon = TopBarIcon(
                resId = R.drawable.ic_back,
                size = 26.dp,
                leftPadding = 16.dp,
                description = "",
                iconClickListener = {},
            ),
        ),
        TopBarState(
            title = "왼쪽 아이콘이 있는 왼쪽 제목",
            textAlign = TopBarTitleAlign.LEFT,
            topPadding = 10.dp,
            bottomPadding = 10.dp,
            leftIcon = TopBarIcon(
                resId = R.drawable.ic_back,
                size = 26.dp,
                leftPadding = 16.dp,
                rightPadding = 8.dp,
                description = "",
                iconClickListener = {},
            ),
        ),
        TopBarState(
            title = "다양한 사이즈 아이콘과 가운데 제목",
            textAlign = TopBarTitleAlign.CENTER,
            leftIcon = TopBarIcon(
                resId = R.drawable.ic_back,
                size = 26.dp,
                leftPadding = 16.dp,
                description = "",
                iconClickListener = {},
            ),
            rightIcon1 = TopBarIcon(
                resId = R.drawable.ic_back,
                size = 20.dp,
                rightPadding = 10.dp,
                description = "",
                iconClickListener = {},
            ),
            rightIcon2 = TopBarIcon(
                resId = R.drawable.ic_back,
                size = 42.dp,
                rightPadding = 10.dp,
                description = "",
                iconClickListener = {},
            ),
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        ),
    )
}

data class TopBarState(
    val title: String,
    val textAlign: TopBarTitleAlign = TopBarTitleAlign.CENTER,
    val leftIcon: TopBarIcon? = null,
    val rightIcon1: TopBarIcon? = null,
    val rightIcon2: TopBarIcon? = null,
    val topPadding: Dp = 0.dp,
    val bottomPadding: Dp = 0.dp,
)

@Preview(showBackground = true)
@Composable
fun TopBarPreview(
    @PreviewParameter(TopBarProvider::class) state: TopBarState,
) {
    TopBar(
        titleText = state.title,
        titleAlign = state.textAlign,
        topPadding = state.topPadding,
        bottomPadding = state.bottomPadding,
        leftIcon = state.leftIcon,
        rightIcon1 = state.rightIcon1,
        rightIcon2 = state.rightIcon2,
    )
}
