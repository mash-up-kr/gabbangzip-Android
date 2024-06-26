package com.mashup.gabbangzip.sharedalbum.presentation.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun rememberSwipeState(maxWidth: Float, maxHeight: Float): SwipeState =
    remember { SwipeState(maxWidth, maxHeight) }

class SwipeState(val maxWidth: Float, val maxHeight: Float) {
    val offsetX = Animatable(0f)
    val offsetY = Animatable(0f)

    fun reset(scope: CoroutineScope) = scope.launch {
        launch { offsetX.animateTo(0f, tween(400)) }
        launch { offsetY.animateTo(0f, tween(400)) }
    }

    fun liked(scope: CoroutineScope) = scope.launch {
        offsetX.animateTo(maxWidth * 2, tween(400))
    }

    fun disliked(scope: CoroutineScope) = scope.launch {
        offsetX.animateTo(-(maxWidth * 2), tween(400))
    }

    fun drag(scope: CoroutineScope, x: Float, y: Float) = scope.launch {
        launch { offsetX.animateTo(x) }
        launch { offsetY.animateTo(y) }
    }
}

fun Modifier.swiper(
    state: SwipeState,
    onDragReset: () -> Unit = {},
    onDragLiked: () -> Unit,
    onDragDisliked: () -> Unit,
): Modifier = composed {
    val scope = rememberCoroutineScope()
    this then Modifier
        .pointerInput(Unit) {
            detectDragGestures(
                onDragEnd = {
                    when {
                        abs(state.offsetX.targetValue) < state.maxWidth / 4 -> {
                            state
                                .reset(scope)
                                .invokeOnCompletion { onDragReset() }
                        }

                        state.offsetX.targetValue > 0 -> {
                            state
                                .liked(scope)
                                .invokeOnCompletion { onDragLiked() }
                        }

                        state.offsetX.targetValue < 0 -> {
                            state
                                .disliked(scope)
                                .invokeOnCompletion { onDragDisliked() }
                        }
                    }
                },
                onDrag = { change, dragAmount ->
                    val original = Offset(state.offsetX.targetValue, state.offsetY.targetValue)
                    val summed = original + dragAmount
                    val newValue = Offset(
                        x = summed.x.coerceIn(-state.maxWidth, state.maxWidth),
                        y = summed.y.coerceIn(-state.maxHeight, state.maxHeight),
                    )
                    if (change.positionChange() != Offset.Zero) change.consume()
                    state.drag(scope, newValue.x, newValue.y)
                },
            )
        }
        .graphicsLayer(
            translationX = state.offsetX.value,
            translationY = state.offsetY.value,
            rotationZ = (state.offsetX.value / 60).coerceIn(-40f, 40f),
            alpha = ((state.maxWidth - abs(state.offsetX.value)) / state.maxWidth).coerceIn(0f, 1f),
        )
}
