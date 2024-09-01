package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicLoadingIndicator
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.GroupCreationUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common.GroupCreationScaffold
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
    val nextButtonEnabled by rememberUpdatedState(newValue = state.thumbnail != null)
    val (modifyButtonEnabled, setModifyButtonEnabled) = remember { mutableStateOf(false) }

    GroupCreationThumbnailScreen(
        state = state,
        nextButtonEnabled = nextButtonEnabled,
        modifyButtonEnabled = modifyButtonEnabled,
        setModifyButtonEnabled = setModifyButtonEnabled,
        onBackButtonClicked = onBackButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        openPhotoPicker = onGetThumbnailButtonClicked,
    )

    LaunchedEffect(isGroupCreated) {
        if (isGroupCreated) {
            navigateNextScreen()
        }
    }
}

@Composable
private fun GroupCreationThumbnailScreen(
    state: GroupCreationUiState,
    nextButtonEnabled: Boolean,
    modifyButtonEnabled: Boolean,
    setModifyButtonEnabled: (Boolean) -> Unit,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    openPhotoPicker: () -> Unit,
) {
    GroupCreationScaffold(
        modifier = Modifier.noRippleClickable(isSingleClick = true) {
            if (modifyButtonEnabled) {
                setModifyButtonEnabled(false)
            }
        },
        contentAlignment = Alignment.CenterHorizontally,
        topBar = {
            PicBackButtonTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Gray0Alpha80)
                    .padding(top = 30.dp, bottom = 14.dp, start = 16.dp, end = 16.dp),
                titleText = stringResource(id = R.string.group_creation_button_name),
                backButtonClicked = onBackButtonClicked,
            )
            PicProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp),
                level = 3,
                total = 4f,
            )
        },
        bottomFloatingButton = {
            PicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
                text = stringResource(id = R.string.next),
                isRippleClickable = true,
                enable = nextButtonEnabled,
                onButtonClicked = onNextButtonClicked,
            )
        },
        content = {
            GroupCreationThumbnailContent(
                thumbnailUri = state.thumbnail,
                groupName = state.name,
                keyword = state.keyword,
                modifyButtonEnabled = modifyButtonEnabled,
                onThumbnailButtonClick = { setModifyButtonEnabled(true) },
                openPhotoPicker = openPhotoPicker,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            )
        },
    )
    PicLoadingIndicator(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray0),
        isVisible = state.isLoading,
    )
}

@Composable
private fun GroupCreationThumbnailContent(
    thumbnailUri: Uri?,
    groupName: String,
    keyword: GroupKeyword,
    modifyButtonEnabled: Boolean,
    onThumbnailButtonClick: () -> Unit,
    openPhotoPicker: () -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 32.dp, bottom = 32.dp),
        text = stringResource(id = R.string.group_add_description),
        style = PicTypography.headBold18,
        color = Gray80,
        textAlign = TextAlign.Center,
    )
    ThumbnailCard(
        modifier = Modifier.size(310.dp, 420.dp),
        thumbnailUri = thumbnailUri,
        groupName = groupName,
        keyword = keyword,
        modifyButtonEnabled = modifyButtonEnabled,
        onThumbnailButtonClick = onThumbnailButtonClick,
        openPhotoPicker = openPhotoPicker,
    )
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
                    .rippleClickable(onClick = openPhotoPicker, isSingleClick = true),
                iconRes = R.drawable.ic_image_add,
                iconContentDescription = R.string.group_add,
                iconColor = Gray80,
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .matchParentSize()
                    .rippleClickable(onClick = onThumbnailButtonClick, isSingleClick = true),
                model = thumbnailUri,
                contentDescription = stringResource(id = R.string.thumbnail_image),
                contentScale = ContentScale.Crop,
            )
            if (modifyButtonEnabled) {
                CardCoverIcon(
                    modifier = Modifier
                        .matchParentSize()
                        .background(BlackAlpha50)
                        .rippleClickable(onClick = openPhotoPicker, isSingleClick = true),
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
        nextButtonEnabled = false,
        modifyButtonEnabled = false,
        setModifyButtonEnabled = {},
        onBackButtonClicked = {},
        onNextButtonClicked = {},
        openPhotoPicker = {},
    )
}
