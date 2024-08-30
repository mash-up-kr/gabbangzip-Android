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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
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
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rippleClickable

@Composable
fun PicButton(
    modifier: Modifier = Modifier,
    text: String,
    enable: Boolean = true,
    isRippleClickable: Boolean = false,
    backgroundColor: Color = Gray80,
    contentColor: Color = Gray0,
    onButtonClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (enable) backgroundColor else SilverSand)
            .then(
                if (isRippleClickable) {
                    Modifier.rippleClickable(enabled = enable, onClick = onButtonClicked)
                } else {
                    Modifier.noRippleClickable(enabled = enable, onClick = onButtonClicked)
                },
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
    isRippleClickable: Boolean = false,
    backgroundColor: Color = Gray80,
    contentColor: Color = Gray0,
    onButtonClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .then(
                if (isRippleClickable) {
                    Modifier.rippleClickable(onClick = onButtonClicked)
                } else {
                    Modifier.noRippleClickable(onClick = onButtonClicked)
                },
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
    text: String = "",
    enable: Boolean = true,
    isRippleClickable: Boolean = false,
    backgroundColor: Color = Gray80,
    contentColor: Color = Gray0,
    @DrawableRes iconRes: Int? = null,
    isHaptic: Boolean = false,
    isSingleClick: Boolean = false,
    onButtonClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(if (enable) backgroundColor else SilverSand)
            .then(
                if (isRippleClickable) {
                    Modifier.rippleClickable(onClick = onButtonClicked, isSingleClick = isSingleClick, isHaptic = isHaptic)
                } else {
                    Modifier.noRippleClickable(onClick = onButtonClicked, isSingleClick = isSingleClick, isHaptic = isHaptic)
                },
            )
            .padding(
                vertical = 16.dp,
                horizontal = if (text.isNotBlank()) 26.dp else 14.dp,
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
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(contentColor),
                )
            }
            if (text.isNotBlank()) {
                Text(
                    text = text,
                    style = PicTypography.bodyMedium16,
                    color = contentColor,
                )
            }
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
                isRippleClickable = true,
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
            PicNormalButton(
                text = "",
                iconRes = R.drawable.ic_share,
            )
        }
    }
}
