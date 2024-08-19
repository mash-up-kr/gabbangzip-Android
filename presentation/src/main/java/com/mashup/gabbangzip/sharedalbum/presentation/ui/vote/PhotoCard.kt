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
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VoteClickInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VotePhoto
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rememberSwipeState
import com.mashup.gabbangzip.sharedalbum.presentation.utils.swiper
import kotlinx.coroutines.delay

@Composable
fun PhotoCard(
    modifier: Modifier = Modifier,
    photo: VotePhoto,
    voteClickInfo: VoteClickInfo,
    currentIndex: Int,
    onVoteBySwiped: (result: PhotoVoteType, photo: VotePhoto) -> Unit,
    content: @Composable () -> Unit,
    onVoteByClicked: (result: PhotoVoteType, photo: VotePhoto) -> Unit,
) {
    val swiped = remember { mutableStateOf(false) }
    val swipeEnable = remember { mutableStateOf(false) }
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
                            onVoteBySwiped(PhotoVoteType.LIKE, photo)
                        },
                        onDragDisliked = {
                            swiped.value = true
                            onVoteBySwiped(PhotoVoteType.DISLIKE, photo)
                        },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                content()
            }
        }

        LaunchedEffect(voteClickInfo) {
            if (voteClickInfo.index == currentIndex && !swiped.value) {
                when (voteClickInfo.type) {
                    PhotoVoteType.LIKE -> {
                        swipeEnable.value = true
                        onVoteByClicked(PhotoVoteType.LIKE, photo)
                        swipeState.liked(coroutineScope)
                        delay(VoteConstant.CARD_DELAY_TIME)
                        swiped.value = true
                        swipeEnable.value = false
                    }

                    PhotoVoteType.DISLIKE -> {
                        swipeEnable.value = true
                        onVoteByClicked(PhotoVoteType.DISLIKE, photo)
                        swipeState.disliked(coroutineScope)
                        delay(VoteConstant.CARD_DELAY_TIME)
                        swiped.value = true
                        swipeEnable.value = false
                    }

                    else -> Unit
                }
            }
        }

        // 버튼 클릭후 1초 딜레이 되는동안 카드 스와이프 막기 위함
        if (swipeEnable.value) {
            DisableDrag(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun DisableDrag(modifier: Modifier) {
    Box(
        modifier = modifier
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
