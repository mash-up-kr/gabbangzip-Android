package com.mashup.gabbangzip.sharedalbum.presentation.ui.onboarding

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
fun HorizontalDotIndicator(
    circleSize: Dp,
    selectedColor: Color,
    unselectedColor: Color,
    @IntRange(from = 1) totalPage: Int,
    @IntRange(from = 1) currentPage: Int,
    paddingHorizontal: Dp,
) {
    Row {
        for (i in 1..totalPage) {
            CircleUi(
                modifier = Modifier.padding(start = if (i > 1) paddingHorizontal else 0.dp),
                size = circleSize,
                color = if (currentPage == i) selectedColor else unselectedColor,
            )
        }
    }
}

@Composable
fun CircleUi(
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
fun HorizontalDotIndicatorPreview() {
    HorizontalDotIndicator(
        circleSize = 50.dp,
        selectedColor = Gray80,
        unselectedColor = Gray50,
        totalPage = 4,
        currentPage = 2,
        paddingHorizontal = 30.dp,
    )
}

@Preview(showBackground = true)
@Composable
fun CircleUiPreview() {
    CircleUi(Modifier, 50.dp, Gray50)
}
