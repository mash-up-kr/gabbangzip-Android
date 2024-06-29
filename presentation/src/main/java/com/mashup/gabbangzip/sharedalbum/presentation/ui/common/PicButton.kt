package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Cultured
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SilverSand
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun PicButton(
    modifier: Modifier = Modifier,
    text: String,
    enable: Boolean = true,
    backgroundColor: Color = Gray80,
    contentColor: Color = Gray0,
    onButtonClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .noRippleClickable(enabled = enable) { onButtonClicked() }
            .background(
                color = if (enable) backgroundColor else SilverSand,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = PicTypography.bodyMedium17,
            color = contentColor,
        )
    }
}

@Composable
fun PicDialogButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Gray80,
    contentColor: Color = Gray0,
    onButtonClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .noRippleClickable { onButtonClicked() }
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(vertical = 18.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = PicTypography.bodyMedium14,
            color = contentColor,
        )
    }
}

@Composable
fun PicNormalButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Gray80,
    contentColor: Color = Gray0,
    @DrawableRes iconRes: Int? = null,
    onButtonClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .noRippleClickable { onButtonClicked() }
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(14.dp),
            )
            .padding(
                vertical = 16.dp,
                horizontal = if (iconRes == null) 26.dp else 30.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconRes != null) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = iconRes),
                    contentDescription = stringResource(R.string.pic_button, text),
                    colorFilter = ColorFilter.tint(contentColor),
                )
            }
            Text(
                text = text,
                style = PicTypography.bodyMedium16,
                color = contentColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PicButtonPreview() {
    SharedAlbumTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            PicButton(
                modifier = Modifier.fillMaxWidth(),
                text = "다음",
            )
            PicButton(
                modifier = Modifier.fillMaxWidth(),
                text = "다음",
                enable = false,
            )
            PicButton(
                modifier = Modifier.fillMaxWidth(),
                text = "다음",
                backgroundColor = Cultured,
                contentColor = Gray80,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PicDialogButton(
                    modifier = Modifier.weight(1f),
                    text = "나가기",
                    backgroundColor = Cultured,
                    contentColor = Gray80,
                )
                PicDialogButton(
                    modifier = Modifier.weight(1f),
                    text = "로그아웃",
                )
            }
            PicNormalButton(
                text = "내 PIC 올리기",
            )
            PicNormalButton(
                text = "링크 복사",
                iconRes = R.drawable.ic_kakao,
            )
        }
    }
}
