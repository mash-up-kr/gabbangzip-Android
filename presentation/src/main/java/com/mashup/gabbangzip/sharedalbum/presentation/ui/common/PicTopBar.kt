package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Coral
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun PicTopBar(
    modifier: Modifier = Modifier,
    rightIcon: PicTopBarIcon,
    rightIconClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 14.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .width(42.dp)
                .height(26.dp),
            painter = painterResource(id = PicTopBarIcon.PIC_LOGO.iconRes),
            contentDescription = stringResource(id = PicTopBarIcon.PIC_LOGO.desc),
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .size(26.dp)
                .noRippleClickable { rightIconClicked() },
            painter = painterResource(id = rightIcon.iconRes),
            contentDescription = stringResource(id = rightIcon.desc),
        )
    }
}

@Composable
fun PicBackButtonTopBar(
    modifier: Modifier = Modifier,
    isLightMode: Boolean = true,
    titleText: String,
    titleAlign: PicTopBarTitleAlign = PicTopBarTitleAlign.CENTER,
    backButtonClicked: () -> Unit,
    rightIcon1: PicTopBarIcon? = null,
    rightIcon2: PicTopBarIcon? = null,
    rightIcon1Clicked: () -> Unit = {},
    rightIcon2Clicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 14.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .noRippleClickable { backButtonClicked() }
                    .size(26.dp),
                painter = painterResource(id = PicTopBarIcon.BACK.iconRes),
                colorFilter = ColorFilter.tint(color = if (isLightMode) Gray80 else Gray0),
                contentDescription = stringResource(id = PicTopBarIcon.BACK.desc),
            )
            if (titleAlign == PicTopBarTitleAlign.LEFT) {
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .weight(1.0f),
                    text = titleText,
                    style = PicTypography.bodyMedium16,
                    color = if (isLightMode) Gray80 else Gray0,
                )
            }
            if (rightIcon1 != null) {
                Image(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(26.dp)
                        .noRippleClickable { rightIcon1Clicked() },
                    painter = painterResource(id = rightIcon1.iconRes),
                    contentDescription = stringResource(id = rightIcon1.desc),
                )
            }
            if (rightIcon2 != null) {
                Image(
                    modifier = Modifier
                        .size(26.dp)
                        .noRippleClickable { rightIcon2Clicked() },
                    painter = painterResource(id = rightIcon2.iconRes),
                    contentDescription = stringResource(id = rightIcon2.desc),
                )
            }
        }
        if (titleAlign == PicTopBarTitleAlign.CENTER) {
            Text(
                modifier = Modifier.fillMaxWidth(1.0f),
                text = titleText,
                textAlign = TextAlign.Center,
                style = PicTypography.bodyMedium16,
                color = if (isLightMode) Gray80 else Gray0,
            )
        }
    }
}

@Composable
fun PicTextOnlyTopBar(
    modifier: Modifier = Modifier,
    titleText: String,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 14.dp, horizontal = 16.dp),
        text = titleText,
        style = PicTypography.bodyMedium16,
        color = Gray80,
        textAlign = TextAlign.Center,
    )
}

enum class PicTopBarTitleAlign {
    CENTER, LEFT
}

@Preview(showBackground = true)
@Composable
private fun PicTopBarPreview() {
    SharedAlbumTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Coral),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            PicTopBar(
                modifier = Modifier
                    .background(Gray0.copy(alpha = 0.2f))
                    .padding(top = 56.dp),
                rightIcon = PicTopBarIcon.USER,
                rightIconClicked = {},
            )
            PicBackButtonTopBar(
                modifier = Modifier
                    .background(Gray0.copy(alpha = 0.2f))
                    .padding(top = 56.dp),
                titleText = "그룹 만들기",
                backButtonClicked = {},
            )
            PicBackButtonTopBar(
                modifier = Modifier
                    .background(Gray0.copy(alpha = 0.2f))
                    .padding(top = 56.dp),
                titleText = "뛰뛰빵빵 가빵집",
                titleAlign = PicTopBarTitleAlign.LEFT,
                backButtonClicked = {},
                rightIcon1 = PicTopBarIcon.PLUS,
                rightIcon2 = PicTopBarIcon.GROUP_MEMBER,
            )
            PicTextOnlyTopBar(
                modifier = Modifier
                    .background(Gray0.copy(alpha = 0.2f))
                    .padding(top = 56.dp),
                titleText = "완료",
            )
        }
    }
}
