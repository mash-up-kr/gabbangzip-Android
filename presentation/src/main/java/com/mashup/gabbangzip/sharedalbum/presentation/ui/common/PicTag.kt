package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme

@Composable
fun PicTag(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = PicTypography.bodyMedium12,
    iconUrl: String = "",
) {
    Box(
        modifier = modifier
            .background(
                color = Gray20,
                shape = RoundedCornerShape(20.dp),
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconUrl.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier.size(10.dp),
                    model = iconUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.pic_tag, text),
                    colorFilter = ColorFilter.tint(Gray50),
                )
            }
            Text(
                text = text,
                style = textStyle,
                color = Gray60,
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
                text = "쉿, 투표중",
            )
            PicTag(
                text = "학교",
                iconUrl = "https://developer.android.com/images/brand/Android_Robot.png",
            )
        }
    }
}
