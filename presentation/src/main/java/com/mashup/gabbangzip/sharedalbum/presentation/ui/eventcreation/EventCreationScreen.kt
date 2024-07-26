package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicDatePickerField
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicGallery
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTextField
import com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.EventCreationActivity.Companion.PICTURES_MAX_COUNT
import com.mashup.gabbangzip.sharedalbum.presentation.utils.hideKeyboardOnOutsideClicked

@Composable
fun EventCreationScreen(
    state: EventCreationState,
    updateDialogState: (Boolean) -> Unit,
    onCompleteButtonClicked: (String) -> Unit,
    onGalleryButtonClicked: () -> Unit,
    onDismissButtonClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var summary by remember { mutableStateOf("") }
    val buttonEnabled by rememberUpdatedState(summary.isNotBlank() && state.pictures.size >= PICTURES_MAX_COUNT)

    if (state.dialogState) {
        PicDialog(
            titleText = stringResource(R.string.vote_dialog_title),
            contentText = stringResource(R.string.vote_dialog_content),
            dismissText = stringResource(R.string.vote_dialog_exit),
            confirmText = stringResource(R.string.vote_dialog_keep_vote),
            onDismiss = {
                updateDialogState(false)
                onDismissButtonClicked()
            },
            onConfirm = { updateDialogState(false) },
        )
    }

    BackHandler(true) {
        updateDialogState(true)
    }

    Column(
        modifier = Modifier.hideKeyboardOnOutsideClicked(),
    ) {
        PicBackButtonTopBar(
            modifier = Modifier
                .background(Gray0Alpha80)
                .padding(top = 16.dp),
            titleText = "이벤트 만들기",
            backButtonClicked = {
                focusManager.clearFocus()
                updateDialogState(true)
            },
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
        ) {
            EventCreationTitle(text = "이벤트 한줄 요약")
            PicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                value = summary,
                onValueChange = { summary = it },
                hint = "이벤트를 한줄로 요약해주세요.",
                maxLength = 10,
            )
            EventCreationTitle(text = "날짜")
            PicDatePickerField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                date = state.date,
            )
            EventCreationTitle(text = "사진 선택")
            LazyRow(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item(1) {
                    PicGallery(
                        currentCount = state.pictures.size,
                        totalCount = PICTURES_MAX_COUNT,
                        onClicked = { onGalleryButtonClicked() },
                    )
                }
                items(state.pictures) { uri ->
                    AsyncImage(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        model = uri,
                        contentDescription = "사진 선택 이미지",
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
            text = "완료",
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

@Preview(showBackground = true)
@Composable
private fun EventCreationScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray0),
    ) {
        EventCreationScreen(
            state = EventCreationState(),
            updateDialogState = {},
            onCompleteButtonClicked = {},
            onGalleryButtonClicked = {},
            onDismissButtonClicked = {},
        )
    }
}
