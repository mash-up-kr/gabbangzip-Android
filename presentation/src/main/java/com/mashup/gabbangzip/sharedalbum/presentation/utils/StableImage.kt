package com.mashup.gabbangzip.sharedalbum.presentation.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun StableImage(
    modifier: Modifier = Modifier,
    @DrawableRes drawableResId: Int,
    contentDescription: String,
    colorFilter: ColorFilter? = null,
) {
    val painter = painterResource(id = drawableResId)
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        colorFilter = colorFilter,
    )
}
