package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupDetailUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.preview.EventHistoryProvider
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

@Composable
fun GroupDetailScreen(
    onClickGroupMemberButton: (GroupKeyword) -> Unit,
    onClickBackButton: () -> Unit,
    onClickOpenPhotoPickerButton: (eventId: Long) -> Unit,
    onClickSendFcmButton: (eventId: Long) -> Unit,
    onClickVoteButton: (eventId: Long) -> Unit,
    onClickShareButton: (Bitmap) -> Unit,
    onClickEventMake: () -> Unit,
    onClickHistoryItem: (HistoryItem) -> Unit,
    onErrorEvent: () -> Unit,
    viewModel: GroupDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isError) {
        onErrorEvent()
    }

    if (state.status == GroupStatusType.EVENT_COMPLETED) {
        viewModel.markEventVisit()
    }

    GroupDetailScreen(
        state = state,
        onClickGroupMemberButton = {
            state.groupInfo
                ?.let {
                    onClickGroupMemberButton(it.keyword)
                }
                ?: onErrorEvent()
        },
        onClickBackButton = onClickBackButton,
        onClickActionButton = { status ->
            when (status) {
                GroupStatusType.BEFORE_MY_UPLOAD -> state.recentEvent?.let { event ->
                    onClickOpenPhotoPickerButton(event.id)
                }

                GroupStatusType.AFTER_MY_UPLOAD, GroupStatusType.AFTER_MY_VOTE -> {
                    state.recentEvent
                        ?.let {
                            onClickSendFcmButton(it.id)
                        }
                        ?: onErrorEvent()
                }

                GroupStatusType.BEFORE_MY_VOTE -> {
                    state.recentEvent
                        ?.let { event ->
                            onClickVoteButton(event.id)
                        }
                        ?: onErrorEvent()
                }

                else -> {}
            }
        },
        onClickEventMake = onClickEventMake,
        onClickShareButton = onClickShareButton,
        onClickHistoryItem = onClickHistoryItem,
    )
}

@Composable
fun GroupDetailScreen(
    state: GroupDetailUiState,
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
    onClickActionButton: (GroupStatusType) -> Unit,
    onClickShareButton: (Bitmap) -> Unit,
    onClickEventMake: () -> Unit,
    onClickHistoryItem: (HistoryItem) -> Unit,
) {
    val isEnabledNewEvent = remember(state.status) {
        state.status == GroupStatusType.EVENT_COMPLETED
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        PicBackButtonTopBar(
            modifier = Modifier.fillMaxWidth(),
            titleText = state.groupInfo?.name.orEmpty(),
            titleAlign = PicTopBarTitleAlign.LEFT,
            backButtonClicked = onClickBackButton,
            rightIcon1 = if (isEnabledNewEvent) PicTopBarIcon.PLUS else null,
            rightIcon2 = PicTopBarIcon.GROUP_MEMBER,
            rightIcon1Clicked = onClickEventMake,
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
    onClickShareButton: (Bitmap) -> Unit,
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
                    thumbnailBackgroundColor = state.groupInfo?.keyword?.behindCardBackGroundColor
                        ?: GroupKeyword.SCHOOL.behindCardBackGroundColor,
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
                images = ImmutableList(state.groupInfo?.cardBackImages.orEmpty()),
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
                    recentEvent = GroupEvent(
                        id = 0,
                        title = "가빵집 MT",
                        date = "2024.11.01",
                        deadline = "2024.11.01",
                    ),
                    status = GroupStatusType.AFTER_MY_VOTE,
                    statusDescription = "몰라몰라",
                    frontImageFrame = PicPhotoFrame.PLUS,
                ),
                status = GroupStatusType.AFTER_MY_VOTE,
                recentEvent = GroupEvent(
                    id = 0,
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
            onClickEventMake = {},
        )
    }
}
