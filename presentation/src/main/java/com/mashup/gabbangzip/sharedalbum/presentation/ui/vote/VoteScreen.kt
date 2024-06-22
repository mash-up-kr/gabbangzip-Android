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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VoteResult
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VoteState

@Composable
fun VoteScreen(
    state: VoteState,
    onCancelVote: () -> Unit,
    onSwiped: (result: VoteResult, photo: Photo) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        CancelVote(
            modifier = Modifier
                .padding(top = 17.dp)
                .align(Alignment.End),
            onCancelVote = onCancelVote,
        )
        UserProfile(
            modifier = Modifier
                .padding(top = 17.dp)
                .align(Alignment.CenterHorizontally),
            state = state,
        )
        VoteCardContainer(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onSwiped = onSwiped,
            state = state,
        )
    }
}

@Composable
fun CancelVote(
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
        contentDescription = null,
    )
}

@Composable
private fun UserProfile(
    modifier: Modifier,
    state: VoteState,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
            model = state.userProfile.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Text(text = context.getString(R.string.vote_user_name, state.userProfile.name))
    }
}

@Composable
private fun VoteCardContainer(
    modifier: Modifier = Modifier,
    onSwiped: (result: VoteResult, photo: Photo) -> Unit,
    state: VoteState,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        state.photoList.list.forEach { photo ->
            VoteCard(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 24.dp,
                    ),
                onSwiped = onSwiped,
                photo = photo,
            )
        }
    }
}
