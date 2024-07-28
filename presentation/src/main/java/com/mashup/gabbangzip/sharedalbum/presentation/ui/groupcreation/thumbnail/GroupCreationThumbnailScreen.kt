package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.BlackAlpha50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.GroupCreationUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common.ThumbnailCardFrame
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rippleClickable

@Composable
fun GroupCreationThumbnailScreen(
    state: GroupCreationUiState,
    isGroupCreated: Boolean,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onGetThumbnailButtonClicked: () -> Unit,
    navigateNextScreen: () -> Unit,
) {
    val buttonEnabled by rememberUpdatedState(newValue = state.thumbnail != null)
    var modifyButtonEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.noRippleClickable {
            if (modifyButtonEnabled) {
                modifyButtonEnabled = false
            }
        },
    ) {
        PicBackButtonTopBar(
            modifier = Modifier
                .background(Gray0Alpha80)
                .padding(top = 16.dp),
            titleText = stringResource(id = R.string.group_creation_button_name),
            backButtonClicked = onBackButtonClicked,
        )
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PicProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
                level = 3,
                total = 4f,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 32.dp),
                text = stringResource(id = R.string.group_add_description),
                style = PicTypography.headBold18,
                color = Gray80,
                textAlign = TextAlign.Center,
            )
            ThumbnailCard(
                modifier = Modifier.size(310.dp, 420.dp),
                thumbnailUri = state.thumbnail,
                groupName = state.name,
                keyword = state.keyword,
                modifyButtonEnabled = modifyButtonEnabled,
                onThumbnailButtonClick = { modifyButtonEnabled = true },
                openPhotoPicker = onGetThumbnailButtonClicked,
            )
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
            text = stringResource(id = R.string.next),
            isRippleClickable = true,
            enable = buttonEnabled,
            onButtonClicked = onNextButtonClicked,
        )
    }

    LaunchedEffect(isGroupCreated) {
        if (isGroupCreated) {
            navigateNextScreen()
        }
    }
}

@Composable
private fun ThumbnailCard(
    modifier: Modifier,
    thumbnailUri: Uri?,
    groupName: String,
    keyword: GroupKeyword,
    modifyButtonEnabled: Boolean,
    onThumbnailButtonClick: () -> Unit,
    openPhotoPicker: () -> Unit,
) {
    ThumbnailCardFrame(
        modifier = modifier,
        groupName = groupName,
        keyword = keyword,
    ) {
        if (thumbnailUri == null) {
            CardCoverIcon(
                modifier = Modifier
                    .matchParentSize()
                    .background(keyword.frameInnerColor)
                    .rippleClickable(onClick = openPhotoPicker),
                iconRes = R.drawable.ic_image_add,
                iconContentDescription = R.string.group_add,
                iconColor = Gray80,
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .matchParentSize()
                    .rippleClickable(onClick = onThumbnailButtonClick),
                model = thumbnailUri,
                contentDescription = stringResource(id = R.string.thumbnail_image),
                contentScale = ContentScale.Crop,
            )
            if (modifyButtonEnabled) {
                CardCoverIcon(
                    modifier = Modifier
                        .matchParentSize()
                        .background(BlackAlpha50)
                        .rippleClickable(onClick = openPhotoPicker),
                    iconRes = R.drawable.ic_image_modify,
                    iconContentDescription = R.string.group_modify,
                    iconColor = Gray0,
                )
            }
        }
    }
}

@Composable
private fun CardCoverIcon(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    @StringRes iconContentDescription: Int,
    iconColor: Color,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        StableImage(
            drawableResId = iconRes,
            contentDescription = stringResource(id = iconContentDescription),
            colorFilter = ColorFilter.tint(iconColor),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupCreationThumbnailScreenPreview() {
    GroupCreationThumbnailScreen(
        state = GroupCreationUiState(
            name = "가빵집",
            keyword = GroupKeyword.EXERCISE,
            thumbnail = null,
            groupCreationResult = null,
        ),
        isGroupCreated = false,
        onBackButtonClicked = {},
        onNextButtonClicked = {},
        onGetThumbnailButtonClicked = {},
        navigateNextScreen = {},
    )
}
