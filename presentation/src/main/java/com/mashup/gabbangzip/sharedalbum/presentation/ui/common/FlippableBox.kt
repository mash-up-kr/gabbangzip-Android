package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable
import kotlin.math.abs

/***
 * 앞면과 뒷면을 가지고 있으며, 카드가 뒤집힐 수 있는 Box Component
 * @param frontScreen 앞면을 표현 하는 Composable Component
 * @param backScreen 뒷면을 표현 하는 Composable Component
 * @param flipAnimationSpec 회전 Animation
 * @param onFrontScreenClick null 이면 클릭 시 화면 뒤집기, 그 이외는 앞면 클릭 시 수행 작업
 * @param onBackScreenClick null 이면 클릭 시 화면 뒤집기, 그 이외는 뒷면 클릭 시 수행 작업
 * @param enableFlipByDrag true 이면 drag 를 통해 카드를 뒤집을 수 있다
 * @sample FlippableBoxPreview
 */
@Composable
fun FlippableBox(
    modifier: Modifier = Modifier,
    frontScreen: @Composable BoxScope.() -> Unit,
    backScreen: @Composable BoxScope.() -> Unit,
    flipAnimationSpec: AnimationSpec<Float>,
    onFrontScreenClick: (() -> Unit)? = null,
    onBackScreenClick: (() -> Unit)? = null,
    enableFlipByDrag: Boolean = false,
) {
    var rotationAngle by remember { mutableFloatStateOf(0f) }
    val nativeAnimatedRotationAngle by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = flipAnimationSpec,
        label = "flippableBoxRotateAnimation",
    )
    val isFront by remember(nativeAnimatedRotationAngle) {
        derivedStateOf {
            val angle = abs(nativeAnimatedRotationAngle % 360)
            angle <= 90f || angle >= 270f
        }
    }

    Box(
        modifier = modifier
            .noRippleClickable {
                if (isFront) {
                    onFrontScreenClick?.invoke() ?: run { rotationAngle += 180f }
                } else {
                    onBackScreenClick?.invoke() ?: run { rotationAngle += 180f }
                }
            }
            .run {
                if (enableFlipByDrag) {
                    pointerInput(Unit) {
                        detectHorizontalDragGestures { _, dragAmount ->
                            val flingThreshold = 100f
                            rotationAngle += (dragAmount / flingThreshold).toInt() * 180f
                        }
                    }
                } else {
                    this
                }
            }
            .graphicsLayer {
                rotationY = nativeAnimatedRotationAngle + if (isFront) 0f else 180f
            },
        content = if (isFront) frontScreen else backScreen,
    )
}

@Preview(showBackground = true)
@Composable
fun FlippableBoxPreview(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        FlippableBox(
            modifier = modifier
                .size(100.dp)
                .align(Alignment.Center),
            frontScreen = {
                SampleFrontScreen(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Red),
                )
            },
            backScreen = {
                SampleBackScreen(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Cyan),
                )
            },
            flipAnimationSpec = tween(
                durationMillis = 700,
                easing = LinearOutSlowInEasing,
            ),
            onFrontScreenClick = null, // 클릭 시 뒤집힘
            onBackScreenClick = { /** 뒷면 클릭 시 수행 하는 작업 **/ },
            enableFlipByDrag = true,
        )
    }
}

@Composable
private fun SampleFrontScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(text = "앞면 입니다")
    }
}

@Composable
private fun SampleBackScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(text = "뒷면 입니다")
    }
}
