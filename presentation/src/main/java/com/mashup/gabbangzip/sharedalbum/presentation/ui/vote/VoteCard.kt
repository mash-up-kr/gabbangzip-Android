package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VoteResult
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rememberSwipeState
import com.mashup.gabbangzip.sharedalbum.presentation.utils.swiper

@Composable
fun VoteCard(
    modifier: Modifier = Modifier,
    onSwiped: (result: VoteResult, photo: Photo) -> Unit,
    photo: Photo,
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
                            onSwiped(VoteResult.LIKE, photo)
                        },
                        onDragDisLiked = {
                            swiped.value = true
                            onSwiped(VoteResult.DISLIKE, photo)
                        },
                    )
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = photo.date)
            }
        }
    }
}
