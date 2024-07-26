package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.detail

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicDatePickerField
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicGallery
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTextField
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.EventCreationActivity.Companion.PICTURES_MAX_COUNT
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.EventCreationState
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.hideKeyboardOnOutsideClicked
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun EventCreationDetailScreen(
    state: EventCreationState,
    onCompleteButtonClicked: (String) -> Unit,
    onGalleryButtonClicked: () -> Unit,
    onPictureDeleteButtonClicked: (Int) -> Unit,
    onDismissButtonClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var showDialog by remember { mutableStateOf(false) }
    var summary by remember { mutableStateOf("") }
    val buttonEnabled by rememberUpdatedState(summary.isNotBlank() && state.pictures.size >= PICTURES_MAX_COUNT)

    if (showDialog) {
        PicDialog(
            titleText = stringResource(R.string.event_creation_dialog_title),
            contentText = stringResource(R.string.event_creation_dialog_desc),
            dismissText = stringResource(R.string.event_creation_dialog_dismiss),
            confirmText = stringResource(R.string.event_creation_dialog_confirm),
            onDismiss = {
                showDialog = false
                onDismissButtonClicked()
            },
            onConfirm = { showDialog = false },
            onDismissRequest = {},
        )
    }

    BackHandler(true) {
        showDialog = true
    }

    Column(
        modifier = Modifier.hideKeyboardOnOutsideClicked(),
    ) {
        PicBackButtonTopBar(
            modifier = Modifier
                .background(Gray0Alpha80)
                .padding(top = 16.dp),
            titleText = stringResource(id = R.string.event_creation),
            backButtonClicked = {
                focusManager.clearFocus()
                showDialog = true
            },
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
        ) {
            EventCreationTitle(stringResource(id = R.string.event_creation_detail_summary))
            PicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                value = summary,
                onValueChange = { summary = it },
                hint = stringResource(id = R.string.event_creation_detail_summary_hint),
                maxLength = 10,
            )
            EventCreationTitle(stringResource(id = R.string.event_creation_detail_date))
            PicDatePickerField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                date = state.date,
            )
            EventCreationTitle(stringResource(id = R.string.event_creation_detail_pictures))
            LazyRow(
                modifier = Modifier.padding(top = 9.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                item(1) {
                    PicGallery(
                        modifier = Modifier.padding(top = 7.dp, end = 7.dp),
                        currentCount = state.pictures.size,
                        totalCount = PICTURES_MAX_COUNT,
                        onClicked = { onGalleryButtonClicked() },
                    )
                }
                itemsIndexed(state.pictures) { idx, uri ->
                    PictureWithDeleteButton(
                        uri = uri,
                        onButtonClicked = { onPictureDeleteButtonClicked(idx) },
                    )
                }
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.event_creation_detail_timeout),
            style = PicTypography.bodyMedium14,
            color = Gray60,
            textAlign = TextAlign.Center,
        )
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 16.dp),
            text = stringResource(id = R.string.complete),
            isRippleClickable = true,
            enable = buttonEnabled,
            onButtonClicked = {
                focusManager.clearFocus()
                onCompleteButtonClicked(summary)
            },
        )
    }
}

@Composable
private fun EventCreationTitle(text: String) {
    Text(
        text = text,
        style = PicTypography.headBold18,
        color = Gray80,
    )
}

@Composable
private fun PictureWithDeleteButton(
    modifier: Modifier = Modifier,
    uri: Uri?,
    onButtonClicked: () -> Unit,
) {
    Box(
        modifier = modifier.size(107.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.BottomStart),
            model = uri,
            contentDescription = stringResource(id = R.string.event_creation_detail_pictures),
            contentScale = ContentScale.Crop,
        )
        StableImage(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .noRippleClickable { onButtonClicked() },
            drawableResId = R.drawable.ic_delete,
            contentDescription = stringResource(id = R.string.event_creation_picture_delete),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EventCreationScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray0),
    ) {
        EventCreationDetailScreen(
            state = EventCreationState(),
            onCompleteButtonClicked = {},
            onGalleryButtonClicked = {},
            onPictureDeleteButtonClicked = {},
            onDismissButtonClicked = {},
        )
    }
}
