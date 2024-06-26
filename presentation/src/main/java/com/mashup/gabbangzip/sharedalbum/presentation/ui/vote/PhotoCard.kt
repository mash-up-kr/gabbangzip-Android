package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteResult
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rememberSwipeState
import com.mashup.gabbangzip.sharedalbum.presentation.utils.swiper

@Composable
fun PhotoCard(
    modifier: Modifier = Modifier,
    photo: Photo,
    content: @Composable () -> Unit,
    onSwiped: (result: PhotoVoteResult, photo: Photo) -> Unit,
) {
    val swiped = remember { mutableStateOf(false) }

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
                            onSwiped(PhotoVoteResult.LIKE, photo)
                        },
                        onDragDisliked = {
                            swiped.value = true
                            onSwiped(PhotoVoteResult.DISLIKE, photo)
                        },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                content()
            }
        }
    }
}
