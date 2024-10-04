package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80

@Composable
fun PicHorizontalDotIndicator(
    circleSize: Dp,
    selectedColor: Color,
    unselectedColor: Color,
    @IntRange(from = 1) totalPage: Int,
    @IntRange(from = 1) currentPage: Int,
    indicatorSpacing: Dp,
) {
    Row {
        for (i in 1..totalPage) {
            CircleUi(
                modifier = Modifier.padding(start = if (i > 1) indicatorSpacing else 0.dp),
                size = circleSize,
                color = if (currentPage == i) selectedColor else unselectedColor,
            )
        }
    }
}

@Composable
private fun CircleUi(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color, shape = CircleShape),
    )
}

@Preview(showBackground = true)
@Composable
private fun HorizontalDotIndicatorPreview() {
    PicHorizontalDotIndicator(
        circleSize = 50.dp,
        selectedColor = Gray80,
        unselectedColor = Gray50,
        totalPage = 4,
        currentPage = 2,
        indicatorSpacing = 30.dp,
    )
}

@Preview(showBackground = true)
@Composable
private fun CircleUiPreview() {
    CircleUi(Modifier, 50.dp, Gray50)
}
