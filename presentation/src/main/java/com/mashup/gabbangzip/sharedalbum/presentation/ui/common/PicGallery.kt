package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun PicGallery(
    modifier: Modifier = Modifier,
    currentCount: Int = 0,
    totalCount: Int = 4,
    onClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Gray20)
            .border(
                width = 1.dp,
                color = Gray50,
                shape = RoundedCornerShape(10.dp),
            )
            .noRippleClickable { onClicked() }
            .padding(horizontal = 36.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        StableImage(
            drawableResId = R.drawable.ic_gallery,
            contentDescription = stringResource(id = R.string.pic_gallery),
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = if (currentCount > 0) Gray80 else Gray60)) {
                    append(currentCount.toString())
                }
                append("/$totalCount")
            },
            style = PicTypography.bodyMedium16,
            color = Gray60,
        )
    }
}

@Preview
@Composable
fun PicGalleryPreview() {
    PicGallery()
}
