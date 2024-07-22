package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.contract.VoteConstant
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VoteClickInfo
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rememberSwipeState
import com.mashup.gabbangzip.sharedalbum.presentation.utils.swiper
import kotlinx.coroutines.delay

@Composable
fun PhotoCard(
    modifier: Modifier = Modifier,
    photo: Photo,
    voteClickInfo: VoteClickInfo,
    currentIndex: Int,
    onSwiped: (result: PhotoVoteType, photo: Photo) -> Unit,
    content: @Composable () -> Unit,
    onVoteClickSwiped: (result: PhotoVoteType, photo: Photo) -> Unit,
) {
    val swiped = remember { mutableStateOf(false) }
    val isSwiped = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(modifier = modifier) {
        val swipeState = rememberSwipeState(
            maxWidth = constraints.maxWidth.toFloat(),
            maxHeight = constraints.maxHeight.toFloat(),
        )
        if (swiped.value.not()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .swiper(
                        state = swipeState,
                        onDragLiked = {
                            swiped.value = true
                            onSwiped(PhotoVoteType.LIKE, photo)
                        },
                        onDragDisliked = {
                            swiped.value = true
                            onSwiped(PhotoVoteType.DISLIKE, photo)
                        },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                content()
            }
        }

        LaunchedEffect(voteClickInfo.index == currentIndex) {
            when (voteClickInfo.type) {
                PhotoVoteType.LIKE -> {
                    isSwiped.value = true
                    onVoteClickSwiped(PhotoVoteType.LIKE, photo)
                    swipeState.liked(coroutineScope)
                    delay(VoteConstant.CARD_DELAY_TIME)
                    swiped.value = true
                    isSwiped.value = false
                }

                PhotoVoteType.DISLIKE -> {
                    isSwiped.value = true
                    onVoteClickSwiped(PhotoVoteType.DISLIKE, photo)
                    swipeState.disliked(coroutineScope)
                    delay(VoteConstant.CARD_DELAY_TIME)
                    swiped.value = true
                    isSwiped.value = false
                }

                else -> Unit
            }
        }

        // 버튼 클릭후 1초 딜레이 되는동안 카드 스와이프 막기 위함
        if (isSwiped.value) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { },
                            onDrag = { _, _ -> },
                            onDragEnd = { },
                            onDragCancel = { },
                        )
                    },
            )
        }
    }
}
