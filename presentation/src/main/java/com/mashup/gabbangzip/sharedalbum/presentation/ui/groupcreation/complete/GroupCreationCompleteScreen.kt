package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTextOnlyTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common.GroupCreationScaffold
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common.ThumbnailCardFrame
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.model.GroupCreationResult

@Composable
fun GroupCreationCompleteScreen(
    groupCreationResult: GroupCreationResult?,
    onNextButtonClicked: () -> Unit,
    showSnackBarMessage: (type: PicSnackbarType, message: String) -> Unit,
) {
    GroupCreationScaffold(
        contentAlignment = Alignment.CenterHorizontally,
        topBar = {
            PicTextOnlyTopBar(
                modifier = Modifier
                    .background(Gray0Alpha80)
                    .padding(top = 20.dp),
                titleText = stringResource(id = R.string.complete),
            )
            PicProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 3.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
                level = 4,
                total = 4f,
            )
        },
        bottomFloatingButton = {
            PicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
                text = stringResource(id = R.string.complete),
                isRippleClickable = true,
                onButtonClicked = onNextButtonClicked,
            )
        },
        content = {
            GroupCreationCompleteContent(
                clipboardManager = LocalClipboardManager.current,
                copyLinkMessage = stringResource(id = R.string.button_copy_link_message),
                invitationMessage = stringResource(id = R.string.invitation_message),
                playStoreUrl = stringResource(id = R.string.play_store_url),
                appStoreUrl = stringResource(id = R.string.app_store_url),
                groupCreationResult = groupCreationResult,
                showSnackBarMessage = showSnackBarMessage
            )
        },
    )
}

@Composable
private fun ColumnScope.GroupCreationCompleteContent(
    groupCreationResult: GroupCreationResult?,
    clipboardManager: ClipboardManager,
    copyLinkMessage: String,
    invitationMessage: String,
    playStoreUrl: String,
    appStoreUrl: String,
    showSnackBarMessage: (type: PicSnackbarType, message: String) -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
        text = stringResource(id = R.string.group_complete_title),
        style = PicTypography.headBold18,
        color = Gray80,
        textAlign = TextAlign.Center,
    )
    if (groupCreationResult != null) {
        ThumbnailCard(
            modifier = Modifier.size(310.dp, 420.dp),
            groupCreationResult = groupCreationResult,
        )
    } else {
        EmptyThumbnailCard()
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 8.dp),
        text = stringResource(id = R.string.group_complete_contents),
        style = PicTypography.bodyMedium14,
        color = Gray60,
        textAlign = TextAlign.Center,
    )
    PicNormalButton(
        text = stringResource(id = R.string.button_copy_link),
        isRippleClickable = true,
        backgroundColor = Gray40,
        contentColor = Gray80,
        iconRes = R.drawable.ic_link,
        onButtonClicked = {
            groupCreationResult?.invitationCode?.let { invitationCode ->
                showSnackBarMessage(PicSnackbarType.CHECK, copyLinkMessage)
                clipboardManager.setText(
                    AnnotatedString(
                        invitationMessage.format(playStoreUrl, appStoreUrl, invitationCode),
                    ),
                )
            }
        },
    )
}

@Composable
private fun ThumbnailCard(
    modifier: Modifier,
    groupCreationResult: GroupCreationResult,
) {
    ThumbnailCardFrame(
        modifier = modifier,
        groupName = groupCreationResult.name,
        keyword = groupCreationResult.keyword,
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            model = groupCreationResult.imageUrl,
            contentDescription = stringResource(id = R.string.thumbnail_image),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun ColumnScope.EmptyThumbnailCard() {
    Box(modifier = Modifier
        .size(310.dp, 420.dp)
        .align(Alignment.CenterHorizontally))
}

@Preview(showBackground = true)
@Composable
private fun GroupCreationCompleteScreenPreview() {
    GroupCreationCompleteScreen(
        groupCreationResult = null,
        onNextButtonClicked = { },
        showSnackBarMessage = { _, _ -> },
    )
}
