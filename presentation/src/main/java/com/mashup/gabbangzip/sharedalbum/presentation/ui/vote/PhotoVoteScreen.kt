package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.UserInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteResult
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteState
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

@Composable
fun VoteScreen(
    state: PhotoVoteState,
    onCancelVote: () -> Unit,
    onSwiped: (result: PhotoVoteResult, photo: Photo) -> Unit,
    onSwipeFinish: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        CancelVoteButton(
            modifier = Modifier
                .padding(top = 17.dp)
                .align(Alignment.End),
            onCancelVote = onCancelVote,
        )

        UserProfile(
            modifier = Modifier
                .padding(top = 17.dp)
                .align(Alignment.CenterHorizontally),
            userInfo = state.userInfo,
        )

        PhotoCardContainer(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .align(Alignment.CenterHorizontally),
            onSwiped = onSwiped,
            photoList = state.photoList,
            onSwipeFinish = onSwipeFinish,
        )
    }
}

@Composable
private fun CancelVoteButton(
    modifier: Modifier,
    onCancelVote: () -> Unit,
) {
    Icon(
        modifier = modifier
            .size(16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Button,
                onClick = onCancelVote,
            ),
        imageVector = Icons.Default.Close,
        contentDescription = stringResource(R.string.cancel_icon),
    )
}

@Composable
private fun UserProfile(
    modifier: Modifier,
    userInfo: UserInfo,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
            model = userInfo.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.profile_image),
        )
        Text(text = stringResource(R.string.vote_user_name, userInfo.name))
    }
}

@Composable
private fun PhotoCardContainer(
    modifier: Modifier = Modifier,
    photoList: ImmutableList<Photo>,
    onSwiped: (result: PhotoVoteResult, photo: Photo) -> Unit,
    onSwipeFinish: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        photoList.forEach { photo ->
            PhotoCard(
                onSwiped = { result, info ->
                    if (info.id == photoList.first().id) {
                        onSwipeFinish()
                    }
                    onSwiped(result, info)
                },
                photo = photo,
                content = {
                    PhotoCardContent(
                        modifier = Modifier.fillMaxSize(),
                        photo = photo,
                    )
                },
            )
        }
    }
}

@Composable
private fun PhotoCardContent(
    modifier: Modifier = Modifier,
    photo: Photo,
) {
    Box(
        modifier = modifier,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            model = photo.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.vote_photo),
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(5.dp),
            text = photo.date,
            color = Color.White,
        )
    }
}
