package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupDetailUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType

@Composable
fun GroupDetailScreen(
    groupId: Long,
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
    viewModel: GroupDetailViewModel = hiltViewModel(),
) {
    // TODO: viewModel state 연결
}

@Composable
fun GroupDetailScreen(
    state: GroupDetailUiState,
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
    onClickActionButton: (GroupStatusType) -> Unit,
    onClickShareButton: () -> Unit,
    onClickHistoryItem: (HistoryItem) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        PicBackButtonTopBar(
            modifier = Modifier.fillMaxWidth(),
            titleText = state.groupInfo?.name.orEmpty(),
            titleAlign = PicTopBarTitleAlign.LEFT,
            backButtonClicked = onClickBackButton,
            rightIcon1 = PicTopBarIcon.PLUS,
            rightIcon2 = PicTopBarIcon.GROUP_MEMBER,
            rightIcon1Clicked = { /* TODO */ },
            rightIcon2Clicked = onClickGroupMemberButton,
        )
        GroupDetailScreenContent(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            onClickActionButton = onClickActionButton,
            onClickShareButton = onClickShareButton,
            onClickHistoryItem = onClickHistoryItem,
        )
    }
}

@Composable
private fun GroupDetailScreenContent(
    modifier: Modifier = Modifier,
    state: GroupDetailUiState,
    onClickActionButton: (GroupStatusType) -> Unit,
    onClickShareButton: () -> Unit,
    onClickHistoryItem: (HistoryItem) -> Unit,
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
                onClickActionButton = onClickActionButton,
                onClickShareButton = onClickShareButton,
            )
            // TODO: 바텀시트면 Spacer 필요없음
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .background(Gray40),
            )
            EventHistoryContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                history = state.history,
                onClickHistoryItem = onClickHistoryItem,
            )
        }
    } else if (state.history.isEmpty()) {
        GroupDetailEmptyContent(modifier = modifier.fillMaxSize())
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
            text = stringResource(id = R.string.group_detail_empty_title),
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
            text = stringResource(id = R.string.group_detail_empty_subtitle),
            style = PicTypography.textNormal14,
            color = Gray60,
        )
        Spacer(modifier = Modifier.weight(3.7f))
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 30.dp),
            text = stringResource(id = R.string.group_creation_button_next),
            isRippleClickable = true,
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
