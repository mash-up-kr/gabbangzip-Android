package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Malibu
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme

@Composable
fun PicTag(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = PicTypography.bodyMedium12,
    @DrawableRes iconRes: Int? = null,
    iconColor: Color = Gray50,
    textColor: Color = Gray80,
) {
    Box(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconRes != null) {
                Image(
                    modifier = Modifier.size(10.dp),
                    painter = painterResource(id = iconRes),
                    contentDescription = stringResource(R.string.pic_tag, text),
                    colorFilter = ColorFilter.tint(iconColor),
                )
            }
            Text(
                text = text,
                style = textStyle,
                color = textColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PicTagPreview() {
    SharedAlbumTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            PicTag(
                modifier = Modifier
                    .background(
                        color = Gray40,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                text = "쉿, 투표중",
            )
            PicTag(
                modifier = Modifier
                    .background(
                        color = Gray40,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                text = "학교",
                iconRes = R.drawable.ic_kakao,
                iconColor = Malibu,
            )
            PicTag(
                modifier = Modifier
                    .background(
                        color = Gray40,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                text = "학교",
                iconRes = R.drawable.ic_kakao,
                iconColor = Malibu,
                textColor = Gray0,
            )
        }
    }
}
