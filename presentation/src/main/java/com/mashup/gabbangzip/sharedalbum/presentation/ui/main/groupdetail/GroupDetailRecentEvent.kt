package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import android.graphics.Bitmap
import android.graphics.Picture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicCroppedPhoto
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicFourPhotoGrid
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicPhotoCardFrame
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.preview.GroupStatusProvider
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupEventActionButtonState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.getActionButtonState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.CardBackImage
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.captureIntoCanvas
import com.mashup.gabbangzip.sharedalbum.presentation.utils.createBitmap

@Composable
fun RecentEventContainer(
    modifier: Modifier = Modifier,
    status: GroupStatusType,
    event: GroupEvent,
    keyword: GroupKeyword,
    cardFrontImageUrl: String,
    images: ImmutableList<CardBackImage>,
    onClickActionButton: (GroupStatusType) -> Unit,
    onClickShareButton: (Bitmap) -> Unit,
) {
    if (status == GroupStatusType.EVENT_COMPLETED ||
        status == GroupStatusType.NO_CURRENT_EVENT
    ) {
        CompletedEventContainer(
            modifier = modifier,
            isFirstVisit = status == GroupStatusType.EVENT_COMPLETED,
            keyword = keyword,
            images = images,
            date = event.date,
            title = event.title,
            onClickShareButton = onClickShareButton,
        )
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RecentEventSummary(event = event)
            PicCroppedPhoto(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(240.dp),
                frameResId = PicPhotoFrame.getTypeByKeyword(keyword.name).frameResId,
                imageUrl = cardFrontImageUrl,
            )
            status.getActionButtonState()?.let { buttonState ->
                RecentEventBottomSection(
                    modifier = Modifier.padding(top = 32.dp),
                    buttonState = buttonState,
                    onClickActionButton = {
                        onClickActionButton(status)
                    },
                )
            }
        }
    }
}

@Composable
private fun CompletedEventContainer(
    modifier: Modifier = Modifier,
    isFirstVisit: Boolean,
    keyword: GroupKeyword,
    date: String,
    title: String,
    images: ImmutableList<CardBackImage>,
    onClickShareButton: (Bitmap) -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (isFirstVisit) {
                Text(
                    text = stringResource(id = R.string.group_detail_event_complete),
                    style = PicTypography.headBold20,
                    color = Gray80,
                )
            }
            PhotoCardWithShareButton(
                modifier = Modifier.fillMaxWidth(),
                keyword = keyword,
                date = date,
                title = title,
                images = images,
                onClickShareButton = onClickShareButton,
            )
        }
        if (isFirstVisit) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_confetti))

            LottieAnimation(
                alignment = Alignment.TopCenter,
                composition = composition,
            )
        }
    }
}

@Composable
private fun PhotoCardWithShareButton(
    modifier: Modifier = Modifier,
    keyword: GroupKeyword,
    date: String,
    title: String,
    images: ImmutableList<CardBackImage>,
    onClickShareButton: (Bitmap) -> Unit,
) {
    val picture = remember { Picture() }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PhotoCard(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 41.5.dp,
                    end = 41.5.dp,
                )
                .captureIntoCanvas(picture),
            keyword = keyword,
            date = date,
            title = title,
            images = images,
        )
        PicNormalButton(
            modifier = Modifier.padding(top = 32.dp),
            iconRes = R.drawable.ic_share,
            onButtonClicked = {
                val bitmap = picture.createBitmap()
                onClickShareButton(bitmap)
            },
        )
    }
}

@Composable
private fun PhotoCard(
    modifier: Modifier = Modifier,
    keyword: GroupKeyword,
    date: String,
    title: String,
    images: ImmutableList<CardBackImage>,
) {
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp.div(2)

    Box(
        modifier = modifier
            .wrapContentSize()
            .heightIn(max = maxHeight),
    ) {
        PicPhotoCardFrame(
            modifier = Modifier.matchParentSize(),
            keywordType = keyword,
            frameResId = PicPhotoFrame.getTypeByKeyword(keyword.name).frameResId,
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp),
            text = date,
            color = Gray80,
            style = PicTypography.bodyMedium16,
        )
        PicFourPhotoGrid(
            modifier = Modifier
                .padding(top = 85.dp, bottom = 85.dp, start = 30.dp, end = 30.dp)
                .align(Alignment.Center),
            backgroundColor = keyword.frontCardBackgroundColor,
            images = images,
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 31.dp),
            text = title,
            color = Gray80,
            style = PicTypography.headBold20,
        )
    }
}

@Composable
private fun RecentEventSummary(
    modifier: Modifier = Modifier,
    event: GroupEvent,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = event.date,
            style = PicTypography.bodyMedium16,
            color = Gray80,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = event.title,
            style = PicTypography.headBold20,
            color = Gray80,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = event.deadline,
            style = PicTypography.textNormal14,
            color = Gray80,
        )
    }
}

@Composable
private fun RecentEventBottomSection(
    modifier: Modifier = Modifier,
    buttonState: GroupEventActionButtonState,
    onClickActionButton: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (buttonState.informationTextResId != null) {
            Text(
                text = stringResource(id = buttonState.informationTextResId),
                style = PicTypography.bodyMedium14,
                color = Gray60,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        PicNormalButton(
            text = buttonState.textResId?.let { resId ->
                stringResource(id = resId)
            }.orEmpty(),
            iconRes = buttonState.iconResId,
            onButtonClicked = onClickActionButton,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun RecentEventPreview(
    @PreviewParameter(GroupStatusProvider::class) status: GroupStatusType,
) {
    SharedAlbumTheme {
        RecentEventContainer(
            status = status,
            event = GroupEvent(
                id = 0,
                title = "가빵집 MT",
                date = "2024.11.03",
                deadline = "6월 14일 월요일 PIC 종료",
            ),
            keyword = GroupKeyword.SCHOOL,
            cardFrontImageUrl = "https://picsum.photos/200/300",
            images = ImmutableList(
                listOf(
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.HAMBURGER,
                    ),
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.PLUS,
                    ),
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.GHOST,
                    ),
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.SEXY,
                    ),
                ),
            ),
            onClickActionButton = {},
            onClickShareButton = {},
        )
    }
}
