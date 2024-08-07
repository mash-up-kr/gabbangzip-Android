package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.UserInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.contract.VoteConstant
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.Photo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.PhotoVoteType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model.VoteClickInfo
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PhotoVoteScreen(
    state: PhotoVoteState,
    onDialogConfirm: () -> Unit,
    onCancelVote: () -> Unit,
    onVoteExit: () -> Unit,
    onVoteBySwiped: (voteType: PhotoVoteType, photo: Photo) -> Unit,
    onVoteByClicked: (result: PhotoVoteType, photo: Photo) -> Unit,
    onVoteClick: (voteType: PhotoVoteType) -> Unit,
    onSwipeFinish: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            if (state.isVoteCancel) {
                PicDialog(
                    titleText = stringResource(R.string.vote_dialog_title),
                    contentText = stringResource(R.string.vote_dialog_content),
                    dismissText = stringResource(R.string.vote_dialog_exit),
                    confirmText = stringResource(R.string.vote_dialog_keep_vote),
                    onDismiss = onVoteExit,
                    onConfirm = onDialogConfirm,
                )
            }

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
                    .padding(top = 24.dp, bottom = 206.dp)
                    .align(Alignment.CenterHorizontally),
                onVoteBySwiped = onVoteBySwiped,
                photoList = state.photoList,
                onSwipeFinish = onSwipeFinish,
                onVoteByClicked = onVoteByClicked,
                voteClickInfo = state.voteClickInfo,
            )
        }

        PhotoVoteButtonContainer(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 68.dp, bottom = 78.dp),
            onVoteClick = onVoteClick,
        )
    }
}

@Composable
private fun PhotoVoteButtonContainer(
    modifier: Modifier,
    onVoteClick: (voteType: PhotoVoteType) -> Unit,
) {
    val (selectedType, setSelectedType) = remember { mutableStateOf(PhotoVoteType.DEFAULT) }
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PhotoVoteType.entries.filter { it != PhotoVoteType.DEFAULT }.forEach { buttonType ->
            PhotoVoteButton(
                modifier = Modifier,
                voteType = buttonType,
                selectedType = selectedType,
                enabled = selectedType == PhotoVoteType.DEFAULT,
                onVoteClick = {
                    setSelectedType(buttonType)
                    onVoteClick(buttonType)
                    coroutineScope.launch {
                        delay(VoteConstant.VOTE_CLICK_DELAY_TIME)
                        setSelectedType(PhotoVoteType.DEFAULT)
                    }
                },
            )
        }
    }
}

@Composable
private fun PhotoVoteButton(
    modifier: Modifier,
    voteType: PhotoVoteType,
    selectedType: PhotoVoteType,
    enabled: Boolean,
    onVoteClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = if (selectedType == voteType) {
                    voteType.alphaColor
                } else {
                    Gray40
                },
            )
            .clickable(
                onClick = onVoteClick,
                enabled = enabled,
            ),
    ) {
        StableImage(
            modifier = Modifier.padding(17.dp),
            drawableResId = voteType.imageResId,
            contentDescription = stringResource(id = voteType.imageDescId),
            colorFilter = ColorFilter.tint(
                color = if (selectedType == PhotoVoteType.DEFAULT) {
                    voteType.mainColor
                } else if (selectedType != voteType) {
                    Gray60
                } else {
                    voteType.mainColor
                },
            ),
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
            .size(24.dp)
            .noRippleClickable { onCancelVote() },
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
        StableImage(
            drawableResId = R.drawable.ic_vote,
            contentDescription = stringResource(R.string.vote_icon),
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(R.string.vote_user_name, userInfo.name),
            style = PicTypography.headBold18,
        )
    }
}

@Composable
private fun PhotoCardContainer(
    modifier: Modifier = Modifier,
    photoList: ImmutableList<Photo>,
    voteClickInfo: VoteClickInfo,
    onVoteBySwiped: (voteType: PhotoVoteType, photo: Photo) -> Unit,
    onSwipeFinish: () -> Unit,
    onVoteByClicked: (result: PhotoVoteType, photo: Photo) -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        photoList.forEachIndexed { index, photo ->
            PhotoCard(
                onVoteBySwiped = { voteType, photoInfo ->
                    onVoteBySwiped(voteType, photoInfo)
                    if (photoInfo.id == photoList.first().id) {
                        onSwipeFinish()
                    }
                },
                photo = photo,
                content = {
                    PhotoCardContent(
                        modifier = Modifier.fillMaxSize(),
                        photo = photo,
                    )
                },
                voteClickInfo = voteClickInfo.copy(index = index),
                currentIndex = photoList.lastIndex - voteClickInfo.index,
                onVoteByClicked = onVoteByClicked,
            )
        }
    }
}

@Composable
private fun PhotoCardContent(
    modifier: Modifier = Modifier,
    photo: Photo,
) {
    AsyncImage(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp)),
        model = photo.imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.vote_photo),
    )
}
