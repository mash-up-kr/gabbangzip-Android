package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.RecentEventContainer
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupDetailUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.preview.EventHistoryProvider
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame

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
    val isEnabledNewEvent = remember(state.status) {
        state.status == GroupStatusType.EVENT_COMPLETED
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray0),
    ) {
        PicBackButtonTopBar(
            modifier = Modifier.fillMaxWidth(),
            titleText = state.groupInfo?.name.orEmpty(),
            titleAlign = PicTopBarTitleAlign.LEFT,
            backButtonClicked = onClickBackButton,
            rightIcon1 = if (isEnabledNewEvent) PicTopBarIcon.PLUS else null,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GroupDetailScreenContent(
    modifier: Modifier = Modifier,
    state: GroupDetailUiState,
    onClickActionButton: (GroupStatusType) -> Unit,
    onClickShareButton: () -> Unit,
    onClickHistoryItem: (HistoryItem) -> Unit,
) {
    if (state.recentEvent != null) {
        val scaffoldState = rememberBottomSheetScaffoldState()

        BottomSheetScaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            sheetShadowElevation = 10.dp,
            sheetPeekHeight = 250.dp,
            sheetContainerColor = Gray0,
            sheetContentColor = Gray0,
            sheetContent = {
                EventHistoryContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    history = state.history,
                    onClickHistoryItem = onClickHistoryItem,
                )
            },
        ) {
            RecentEventContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray0)
                    .padding(top = 10.dp),
                status = state.status,
                event = state.recentEvent,
                keyword = state.groupInfo?.keyword ?: GroupKeyword.SCHOOL,
                cardFrontImageUrl = state.groupInfo?.cardFrontImageUrl.orEmpty(),
                onClickActionButton = onClickActionButton,
                onClickShareButton = onClickShareButton,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun GroupDetailScreenPreview(
    @PreviewParameter(EventHistoryProvider::class) history: List<HistoryItem>,
) {
    SharedAlbumTheme {
        GroupDetailScreen(
            state = GroupDetailUiState(
                groupInfo = GroupInfo(
                    id = 0,
                    cardBackImages = emptyList(),
                    cardFrontImageUrl = "https://picsum.photos/200/300",
                    keyword = GroupKeyword.SCHOOL,
                    name = "가빵집가빵집",
                    recentEventDate = "2024.11.01",
                    status = GroupStatusType.AFTER_MY_VOTE,
                    statusDescription = "몰라몰라",
                    frontImageFrame = PicPhotoFrame.PLUS,
                ),
                status = GroupStatusType.AFTER_MY_VOTE,
                recentEvent = GroupEvent(
                    title = "가빵집 MT",
                    date = "2024.11.01",
                    deadline = "2024.11.01",
                ),
                history = history,
            ),
            onClickActionButton = {},
            onClickShareButton = {},
            onClickBackButton = {},
            onClickHistoryItem = {},
            onClickGroupMemberButton = {},
        )
    }
}
