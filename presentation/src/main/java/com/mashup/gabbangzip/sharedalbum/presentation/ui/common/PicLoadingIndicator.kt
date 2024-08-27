package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Conifer
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Coral
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Dandelion
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Lavender
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPink
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Malibu
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlue

private val colorList = listOf(
    Conifer,
    MayaBlue,
    MagentaPink,
    Lavender,
    Coral,
    Dandelion,
    Malibu,
)

@Composable
fun PicLoadingIndicator(modifier: Modifier, isVisible: Boolean) {
    if (isVisible) {
        val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")
        val transition = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 10_000, easing = LinearEasing),
            ),
            label = "InfiniteTransition",
        )
        val currentColorIndex = ((transition.value * colorList.size).toInt() % colorList.size)
        val loadingColor by animateColorAsState(
            targetValue = colorList[currentColorIndex],
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
            label = "LoadingAnimation",
        )

        Box(
            modifier = modifier,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = loadingColor,
            )
        }
    }
}

@Preview
@Composable
private fun PicLoadingScreenPreview() {
    PicLoadingIndicator(
        modifier = Modifier.fillMaxSize(),
        isVisible = true,
    )
}
