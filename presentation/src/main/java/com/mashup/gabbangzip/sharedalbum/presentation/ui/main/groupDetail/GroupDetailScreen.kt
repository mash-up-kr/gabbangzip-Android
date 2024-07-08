package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.theme.pretendard
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model.GroupDetailUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model.GroupEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage

@Composable
fun GroupDetailScreen(
    groupId: Long,
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    // TODO: viewModel state 연결
}

@Composable
fun GroupDetailScreen(
    state: GroupDetailUiState,
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        PicBackButtonTopBar(
            modifier = Modifier.fillMaxWidth(),
            titleText = "그룹 이름",
            titleAlign = PicTopBarTitleAlign.LEFT,
            backButtonClicked = onClickBackButton,
            rightIcon1 = PicTopBarIcon.PLUS,
            rightIcon2 = PicTopBarIcon.GROUP_MEMBER,
            rightIcon1Clicked = { /* TODO */ },
            rightIcon2Clicked = onClickGroupMemberButton,
        )
    }
    GroupDetailScreenContent(
        modifier = Modifier.fillMaxWidth(),
        state = state,
    )
}

@Composable
private fun GroupDetailScreenContent(
    modifier: Modifier = Modifier,
    state: GroupDetailUiState,
) {
    if (state.recentEvent != null) {
        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .verticalScroll(scrollState),
        ) {
            RecentEventContainer(
                modifier = Modifier.fillMaxWidth(),
                event = state.recentEvent,
            )
            // TODO: 바텀시트면 Spacer 필요없음
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .background(Gray40),
            )
            EventHistoryContainer(
                modifier = Modifier.fillMaxWidth(),
                history = state.history,
            )
        }
    } else if (state.history.isEmpty()) {
        GroupDetailEmptyContent(modifier = modifier.fillMaxSize())
    }
}

@Composable
private fun RecentEventContainer(
    modifier: Modifier = Modifier,
    event: GroupEvent,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (event.status == GroupStatusType.EVENT_COMPLETED) {
            Text(
                text = "네컷 사진이 만들어졌어요!",
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = (-0.02).em,
            )
        } else {
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
}

@Composable
private fun EventHistoryContainer(
    modifier: Modifier = Modifier,
    history: ImmutableList<HistoryItem>,
) {
    if (history.isEmpty()) {
        EventHistoryEmptyContent(modifier = modifier)
    } else {
        EventHistoryGridContent(
            modifier = modifier,
            history = history,
        )
    }
}

@Composable
private fun EventHistoryGridContent(
    modifier: Modifier = Modifier,
    history: ImmutableList<HistoryItem>,
) {

}

@Composable
private fun EventHistoryEmptyContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(43.dp))
        StableImage(
            drawableResId = R.drawable.ic_empty_history,
            contentDescription = "역대 이벤트 기본 이미지",
        )
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 45.dp),
            text = "그룹 이벤트를 만들고\n우리끼리 PIC으로 인생 네컷을 모아보세요.",
            style = PicTypography.textNormal14,
            color = Gray60,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun GroupDetailEmptyContent(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.change_shape))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "이벤트를 생성하고\n함께한 순간을 공유해요!",
            style = PicTypography.headBold20,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        LottieAnimation(
            modifier = Modifier.size(164.dp),
            composition = composition,
            progress = { progress },
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "공유한 사진을 함께 PIC하면 네컷사진을 만들 수 있어요.",
            style = PicTypography.textNormal14,
            color = Gray60,
        )
        Spacer(modifier = Modifier.weight(3.7f))
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 30.dp),
            text = "다음",
            isRippleClickable = true,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun RecentEventPreview() {
    SharedAlbumTheme {
        RecentEventContainer(
            event = GroupEvent(
                title = "가빵집 MT",
                date = "2024.11.03",
                status = GroupStatusType.AFTER_MY_UPLOAD,
                deadline = "6월 14일 월요일 PIC 종료",
            ),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun EmptyHistoryPreview() {
    SharedAlbumTheme {
        EventHistoryEmptyContent(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun EmptyScreenPreview() {
    SharedAlbumTheme {
        GroupDetailEmptyContent(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
