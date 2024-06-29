package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Malibu
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
private fun PicKeywordButton(
    modifier: Modifier = Modifier,
    text: String,
    imageUrl: String,
    selected: Boolean,
    selectedColor: Color,
    textStyle: TextStyle = PicTypography.bodyMedium16,
    onButtonClicked: (String, Boolean) -> Unit,
) {
    Box(
        modifier = modifier
            .noRippleClickable { onButtonClicked(text, selected) }
            .background(
                color = if (selected) selectedColor.copy(alpha = 0.3f) else Gray40,
                shape = RoundedCornerShape(20.dp),
            )
            .padding(horizontal = 28.dp, vertical = 18.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = text,
                style = textStyle,
                color = if (selected) Gray80 else Gray60,
            )
            AsyncImage(
                modifier = Modifier.size(58.dp),
                model = imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.pic_keyword_button, text),
                colorFilter = ColorFilter.tint(if (selected) selectedColor else Gray50),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PicKeywordButtonPreview() {
    SharedAlbumTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            PicKeywordButton(
                text = "학교",
                imageUrl = "https://developer.android.com/images/brand/Android_Robot.png",
                selected = true,
                selectedColor = Malibu,
            ) { _, _ -> }
            PicKeywordButton(
                text = "학교",
                imageUrl = "https://developer.android.com/images/brand/Android_Robot.png",
                selected = false,
                selectedColor = Malibu,
            ) { _, _ -> }
        }
    }
}
