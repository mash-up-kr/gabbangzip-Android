package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.theme.pretendard
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model.GroupEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model.GroupEventActionButtonState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model.getActionButtonState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType

@Composable
fun RecentEventContainer(
    modifier: Modifier = Modifier,
    event: GroupEvent,
    onClickActionButton: (GroupStatusType) -> Unit,
    onClickShareButton: () -> Unit,
) {
    if (event.status == GroupStatusType.EVENT_COMPLETED) {
        CompletedEventContainer(
            modifier = modifier,
            onClickShareButton = onClickShareButton,
        )
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RecentEventSummary(event = event)
            // TODO: 사진 프레임으로 교체 필요
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(240.dp)
                    .background(color = Gray60),
            )
            event.status.getActionButtonState()?.let { buttonState ->
                RecentEventBottomSection(
                    modifier = Modifier.padding(top = 32.dp),
                    buttonState = buttonState,
                    onClickActionButton = {
                        onClickActionButton(event.status)
                    },
                )
            }
        }
    }
}

@Composable
private fun CompletedEventContainer(
    modifier: Modifier = Modifier,
    onClickShareButton: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_confetti))

    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.group_detail_event_complete),
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = (-0.02).em,
            )
            // TODO: 사진 프레임으로 교체 필요
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(240.dp)
                    .background(color = Gray60),
            )
            PicNormalButton(
                modifier = Modifier.padding(top = 32.dp),
                iconRes = R.drawable.ic_share,
                onButtonClicked = { onClickShareButton() },
            )
        }
        LottieAnimation(
            alignment = Alignment.TopCenter,
            composition = composition,
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
        if (buttonState.informationText.isNotBlank()) {
            Text(
                text = buttonState.informationText,
                style = PicTypography.bodyMedium14,
                color = Gray60,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        PicNormalButton(
            text = buttonState.text,
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
            event = GroupEvent(
                title = "가빵집 MT",
                date = "2024.11.03",
                status = status,
                deadline = "6월 14일 월요일 PIC 종료",
            ),
            onClickActionButton = {},
            onClickShareButton = {},
        )
    }
}
