package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
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
    onClickHistoryItem: (HistoryDetailState) -> Unit,
    onShowSnackbar: (PicSnackbarType, String) -> Unit,
    viewModel: GroupDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val errorRetryMessage = stringResource(id = R.string.error_retry)

    state.errorMessage?.let { errorMessage ->
        onShowSnackbar(PicSnackbarType.WARNING, stringResource(id = errorMessage))
    }

    if (state.status == GroupStatusType.EVENT_COMPLETED) {
        viewModel.markEventVisit()
    }

    GroupDetailScreen(
        state = state,
        onClickGroupMemberButton = {
            state.groupInfo?.let {
                onClickGroupMemberButton(it.keyword)
            } ?: run {
                onShowSnackbar(PicSnackbarType.WARNING, errorRetryMessage)
            }
        },
        onClickBackButton = onClickBackButton,
        onClickActionButton = { status ->
            when (status) {
                GroupStatusType.BEFORE_MY_UPLOAD -> state.recentEvent?.let { event ->
                    onClickOpenPhotoPickerButton(event.id)
                }

                GroupStatusType.AFTER_MY_UPLOAD, GroupStatusType.AFTER_MY_VOTE -> {
                    state.recentEvent?.let {
                        onClickSendFcmButton(it.id)
                    } ?: run {
                        onShowSnackbar(PicSnackbarType.WARNING, errorRetryMessage)
                    }
                }

                GroupStatusType.BEFORE_MY_VOTE -> {
                    state.recentEvent?.let { event ->
                        onClickVoteButton(event.id)
                    } ?: run {
                        onShowSnackbar(PicSnackbarType.WARNING, errorRetryMessage)
                    }
                }

                else -> {}
            }
        },
        onClickEventMake = onClickEventMake,
        onClickShareButton = onClickShareButton,
        onClickHistoryItem = { history ->
            state.groupInfo?.run {
                Log.d("TAG", "GroupDetailScreen: 히스토리")
                onClickHistoryItem(
                    HistoryDetailState(
                        groupName = name,
                        keyword = keyword.name,
                        history = history,
                    ),
                )
            } ?: run {
                onShowSnackbar(PicSnackbarType.WARNING, errorRetryMessage)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
    if (state.recentEvent != null) {
        val scaffoldState = rememberBottomSheetScaffoldState()
        val sheetElevation by animateDpAsState(
            targetValue = if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) 0.dp else 16.dp,
        )

        BottomSheetScaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                PicBackButtonTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    titleText = state.groupInfo?.name.orEmpty(),
                    titleAlign = PicTopBarTitleAlign.LEFT,
                    backButtonClicked = onClickBackButton,
                    rightIcon1 = if (state.isEnabledNewEvent) PicTopBarIcon.PLUS else null,
                    rightIcon2 = PicTopBarIcon.GROUP_MEMBER,
                    rightIcon1Clicked = onClickEventMake,
                    rightIcon2Clicked = onClickGroupMemberButton,
                )
            },
            scaffoldState = scaffoldState,
            sheetShadowElevation = sheetElevation,
            sheetPeekHeight = 250.dp,
            sheetContainerColor = Gray0,
            sheetContentColor = Gray0,
            sheetContent = {
                EventHistoryContainer(
                    modifier = Modifier
                        .fillMaxSize()
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
                    .verticalScroll(rememberScrollState())
                    .padding(top = 10.dp, bottom = 270.dp),
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
