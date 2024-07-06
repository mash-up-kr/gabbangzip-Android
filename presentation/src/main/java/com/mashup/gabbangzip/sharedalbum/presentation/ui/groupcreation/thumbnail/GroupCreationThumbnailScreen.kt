package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.thumbnail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rippleClickable

@Composable
fun GroupCreationThumbnailScreen(
    thumbnailUri: Uri?,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onGetThumbnailButtonClicked: () -> Unit,
) {
    val buttonEnabled by rememberUpdatedState(newValue = thumbnailUri != null)
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
                text = "그룹의 대표 사진을 추가해 주세요", // Todo : resource 로 정리
                style = PicTypography.headBold18,
                color = Gray80,
                textAlign = TextAlign.Center,
            )
            Box(
                modifier = Modifier
                    .size(310.dp, 420.dp)
                    .background(Gray50)
                    .rippleClickable(
                        onClick = {
                            if (buttonEnabled.not()) {
                                onGetThumbnailButtonClicked() // 이미지 X
                            } else if (modifyButtonEnabled.not()) {
                                modifyButtonEnabled = true // 이미지 O, 수정 아이콘 X
                            } else {
                                onGetThumbnailButtonClicked() // 이미지 O, 수정 아이콘 O
                            }
                        },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                if (thumbnailUri == null) {
                    StableImage(
                        drawableResId = R.drawable.ic_plus, // Todo : 이미지 추가 아이콘으로 변경 + resource 로 정리
                        contentDescription = "이미지 추가", // Todo : resource 로 정리
                    )
                } else {
                    AsyncImage(
                        model = thumbnailUri,
                        contentDescription = "썸네일 이미지", // Todo : resource 로 정리
                    )
                    if (modifyButtonEnabled) {
                        StableImage(
                            drawableResId = R.drawable.ic_check, // Todo : 이미지 추가 아이콘으로 변경 + resource 로 정리
                            contentDescription = "이미지 추가", // Todo : resource 로 정리
                        )
                    }
                }
            }
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
            text = stringResource(id = R.string.group_creation_button_next),
            isRippleClickable = true,
            enable = buttonEnabled,
            onButtonClicked = onNextButtonClicked,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupCreationThumbnailScreenPreview() {
    GroupCreationThumbnailScreen(
        thumbnailUri = null,
        onBackButtonClicked = {},
        onNextButtonClicked = {},
        onGetThumbnailButtonClicked = {},
    )
}
