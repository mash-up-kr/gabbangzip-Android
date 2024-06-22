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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.R

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
                    modifier = Modifier.size(leftIconSize).padding(end = titleIconLeftPadding),
                    painter = painterResource(id = leftIconRes),
                    contentDescription = leftIconDescription,
                )
            }
            Text(
                modifier = Modifier.weight(1.0f),
                text = if (titleAlign == TopBarTitleAlign.LEFT) titleText else "",
            )
            if (rightIconRes != null) {
                Image(
                    modifier = Modifier.size(rightIconSize).padding(end = rightIconRightPadding),
                    painter = painterResource(id = rightIconRes),
                    contentDescription = rightIconDescription,
                )
            }
            if (rightIcon2Res != null) {
                Image(
                    modifier = Modifier.size(rightIcon2Size).padding(end = rightIcon2RightPadding),
                    painter = painterResource(id = rightIcon2Res),
                    contentDescription = rightIcon2Description,
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
            )
        }
    }
}

enum class TopBarTitleAlign {
    CENTER, LEFT
}

class TopBarProvider : PreviewParameterProvider<TopBarState> {
    override val values: Sequence<TopBarState> = sequenceOf(
        TopBarState(
            title = "제목 가운데",
            textAlign = TopBarTitleAlign.CENTER,
        ),
        TopBarState(
            title = "왼쪽 아이콘이 있는 가운데 제목",
            textAlign = TopBarTitleAlign.CENTER,
            leftIconRes = R.drawable.ic_call_answer,
            leftIconSize = 26.dp,
            leftIconLeftPadding = 16.dp,
            leftIconDescription = "",
        ),
        TopBarState(
            title = "왼쪽 아이콘이 있는 왼쪽 제목",
            textAlign = TopBarTitleAlign.LEFT,
            leftIconRes = R.drawable.ic_call_answer,
            leftIconSize = 26.dp,
            leftIconLeftPadding = 16.dp,
            leftIconDescription = "",
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        ),
        TopBarState(
            title = "사이즈 다양한 아이콘이 가득한 가운데 제목",
            textAlign = TopBarTitleAlign.CENTER,
            leftIconRes = R.drawable.ic_call_answer,
            leftIconSize = 26.dp,
            leftIconLeftPadding = 16.dp,
            leftIconDescription = "",
            rightIconRes = R.drawable.ic_call_answer_video,
            rightIconSize = 20.dp,
            rightIconRightPadding = 10.dp,
            rightIconDescription = "",
            rightIcon2Res = R.drawable.ic_call_answer_video_low,
            rightIcon2Size = 42.dp,
            rightIcon2RightPadding = 10.dp,
            rightIcon2Description = "",
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        ),
    )
}

data class TopBarState(
    val title: String,
    val textAlign: TopBarTitleAlign = TopBarTitleAlign.CENTER,
    val leftIconRes: Int? = null,
    val rightIconRes: Int? = null,
    val rightIcon2Res: Int? = null,
    val leftIconDescription: String? = null,
    val rightIconDescription: String? = null,
    val rightIcon2Description: String? = null,
    val leftIconSize: Dp = 26.dp,
    val rightIconSize: Dp = 26.dp,
    val rightIcon2Size: Dp = 26.dp,
    val topPadding: Dp = 0.dp,
    val bottomPadding: Dp = 0.dp,
    val leftIconLeftPadding: Dp = 0.dp,
    val titleIconLeftPadding: Dp = 0.dp,
    val rightIconRightPadding: Dp = 0.dp,
    val rightIcon2RightPadding: Dp = 0.dp,
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
        leftIconRes = state.leftIconRes,
        rightIconRes = state.rightIconRes,
        rightIcon2Res = state.rightIcon2Res,
        leftIconDescription = state.leftIconDescription,
        rightIconDescription = state.rightIconDescription,
        rightIcon2Description = state.rightIcon2Description,
        leftIconSize = state.leftIconSize,
        rightIconSize = state.rightIconSize,
        rightIcon2Size = state.rightIcon2Size,
        leftIconLeftPadding = state.leftIconLeftPadding,
        titleIconLeftPadding = state.titleIconLeftPadding,
        rightIconRightPadding = state.rightIconRightPadding,
        rightIcon2RightPadding = state.rightIcon2RightPadding,
    )
}
