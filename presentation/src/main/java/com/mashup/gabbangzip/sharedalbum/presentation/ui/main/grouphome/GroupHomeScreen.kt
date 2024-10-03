package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray100
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Malibu
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.FlippableBox
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicLoadingIndicator
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTag
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.CardBackImage
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.ClickType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.FilterTag
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupHomeUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.ViewType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun GroupHomeScreen(
    innerPadding: PaddingValues,
    onClickGroupDetail: (id: Long) -> Unit,
    onClickEventMake: (Long) -> Unit,
    onClickMyPage: () -> Unit,
    onClickGroupEnter: () -> Unit,
    onClickGroupMake: () -> Unit,
    onClickSendFcmButton: (eventId: Long) -> Unit,
    navigateToGroupCreationAndFinish: () -> Unit,
    onNavigateGallery: (eventId: Long) -> Unit,
    onNavigateVote: (eventId: Long) -> Unit,
    onShowSnackbar: (PicSnackbarType, String) -> Unit,
    viewModel: GroupHomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (val groupHomeUiState = state) {
        is GroupHomeUiState.NoGroup -> {
            navigateToGroupCreationAndFinish()
        }

        is GroupHomeUiState.GroupList -> {
            GroupHomeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                groupList = groupHomeUiState.groupList,
                filterTagList = groupHomeUiState.filterTagList,
                viewType = groupHomeUiState.viewType,
                onClickGroupDetail = onClickGroupDetail,
                onClickEventMake = onClickEventMake,
                onClickMyPage = onClickMyPage,
                onClickGroupEnter = onClickGroupEnter,
                onClickGroupMake = onClickGroupMake,
                onClickSendFcmButton = onClickSendFcmButton,
                onNavigateVote = onNavigateVote,
                onNavigateGallery = onNavigateGallery,
                onClickFilterTag = viewModel::clickedFilterTag,
                onClickViewType = viewModel::clickedViewType,
            )
        }

        is GroupHomeUiState.Error -> {
            val message = (state as GroupHomeUiState.Error).errorMessage
            onShowSnackbar(PicSnackbarType.WARNING, stringResource(id = message))
        }

        is GroupHomeUiState.Loading -> {
            PicLoadingIndicator(modifier = Modifier.fillMaxSize(), isVisible = true)
        }

        else -> {}
    }
}

@Composable
fun GroupHomeScreen(
    modifier: Modifier,
    groupList: ImmutableList<GroupInfo>,
    filterTagList: ImmutableList<FilterTag>,
    viewType: ViewType,
    onClickGroupDetail: (id: Long) -> Unit,
    onClickEventMake: (Long) -> Unit,
    onClickMyPage: () -> Unit,
    onClickGroupEnter: () -> Unit,
    onClickGroupMake: () -> Unit,
    onClickSendFcmButton: (eventId: Long) -> Unit,
    onNavigateGallery: (eventId: Long) -> Unit,
    onNavigateVote: (eventId: Long) -> Unit,
    onClickFilterTag: (FilterTag) -> Unit,
    onClickViewType: (ViewType) -> Unit,
) {
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
        ) {
            PicTopBar(
                modifier = Modifier.padding(top = 16.dp),
                rightIcon = PicTopBarIcon.USER,
                rightIconClicked = onClickMyPage,
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                TagFilter(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    filterTagList = filterTagList,
                    onTagClicked = onClickFilterTag,
                )
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .wrapContentHeight()
                        .background(
                            brush = Brush.linearGradient(
                                listOf(
                                    Color.Transparent,
                                    Gray0,
                                    Gray0,
                                ),
                            ),
                        )
                        .align(Alignment.CenterEnd),
                ) {
                    ViewTypeButton(
                        modifier = Modifier
                            .noRippleClickable {
                                when (viewType) {
                                    ViewType.List -> onClickViewType(ViewType.Grid)
                                    ViewType.Grid -> onClickViewType(ViewType.List)
                                }
                            }
                            .padding(top = 11.dp, bottom = 11.dp, end = 20.dp)
                            .wrapContentSize()
                            .align(Alignment.CenterEnd),
                        currentViewType = viewType,
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .height(8.dp)
                    .background(Gray20),
            )

            when (viewType) {
                ViewType.List -> {
                    GroupContainerList(
                        groupList = groupList,
                        onClickGroupDetail = onClickGroupDetail,
                        onClickEventMake = onClickEventMake,
                        onClickSendFcmButton = onClickSendFcmButton,
                        onNavigateGallery = onNavigateGallery,
                        onNavigateVote = onNavigateVote,
                    )
                }

                ViewType.Grid -> {
                    GroupContainerGrid(
                        groupList = groupList,
                        onClickGroupDetail = onClickGroupDetail,
                    )
                }
            }
        }

        GroupFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 45.dp, end = 16.dp),
            onClickGroupMake = onClickGroupMake,
            onClickGroupEnter = onClickGroupEnter,
        )
    }
}

@Composable
private fun ViewTypeButton(
    modifier: Modifier,
    currentViewType: ViewType,
) {
    StableImage(
        modifier = modifier,
        drawableResId = when (currentViewType) {
            ViewType.List -> R.drawable.align_grid
            ViewType.Grid -> R.drawable.align_list
        },
        contentDescription = stringResource(
            id = when (currentViewType) {
                ViewType.List -> R.string.align_grid
                ViewType.Grid -> R.string.align_list
            },
        ),
        colorFilter = ColorFilter.tint(color = Gray50),
    )
}

@Composable
private fun GroupContainerList(
    groupList: ImmutableList<GroupInfo>,
    onClickGroupDetail: (id: Long) -> Unit,
    onClickEventMake: (Long) -> Unit,
    onClickSendFcmButton: (eventId: Long) -> Unit,
    onNavigateGallery: (eventId: Long) -> Unit,
    onNavigateVote: (eventId: Long) -> Unit,
) {
    LazyColumn {
        itemsIndexed(groupList) { index, groupInfo ->
            GroupContainer(
                modifier = if (index == 0) {
                    Modifier.padding(top = 24.dp)
                } else if (index == groupList.lastIndex) {
                    Modifier.padding(bottom = 16.dp)
                } else {
                    Modifier
                },
                groupInfo = groupInfo,
                onGroupDetailClick = onClickGroupDetail,
                onClickSendFcmButton = onClickSendFcmButton,
                onClickEventMake = onClickEventMake,
                onNavigateVote = onNavigateVote,
                onNavigateGallery = onNavigateGallery,
            )

            if (groupList.lastIndex != index) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 46.dp, bottom = 24.dp)
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(color = Gray20),
                )
            }
        }
    }
}

@Composable
private fun GroupContainerGrid(
    groupList: ImmutableList<GroupInfo>,
    onClickGroupDetail: (id: Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 18.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalArrangement = Arrangement.spacedBy(21.dp),
    ) {
        items(items = groupList) { groupInfo ->
            GroupContainerGridItem(
                groupInfo = groupInfo,
                onClickGroupDetail = onClickGroupDetail,
            )
        }
    }
}

@Composable
private fun GroupContainerGridItem(
    groupInfo: GroupInfo,
    onClickGroupDetail: (id: Long) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .noRippleClickable { onClickGroupDetail(groupInfo.id) },
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        FrontCardImage(
            modifier = Modifier.fillMaxSize(),
            frameResId = groupInfo.frontImageFrame.frameResId,
            imageUrl = groupInfo.cardFrontImageUrl,
            backgroundColor = Gray0,
        )
        Text(
            text = groupInfo.name,
            style = PicTypography.headBold16,
            color = Gray80,
        )
        GroupTag(
            modifier = Modifier.fillMaxWidth(),
            keyword = groupInfo.keyword,
            statusDesc = groupInfo.statusDescription,
        )
    }
}

@Composable
private fun TagFilter(
    modifier: Modifier,
    filterTagList: ImmutableList<FilterTag>,
    onTagClicked: (FilterTag) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(start = 10.dp, end = 70.dp),
    ) {
        itemsIndexed(filterTagList, key = { _, item -> item.name }) { _, tagInfo ->
            PicTag(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 3.5.dp),
                text = stringResource(id = tagInfo.tagNameResId),
                iconRes = tagInfo.symbolResId,
                iconColor = tagInfo.symbolColor,
                textColor = if (tagInfo.isSelected) Gray0 else Gray80,
                textStyle = PicTypography.headBold14,
                backgroundColor = if (tagInfo.isSelected) Gray100 else Gray40,
                innerPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                onClick = { onTagClicked(tagInfo) },
            )
        }
    }
}

@Composable
private fun GroupContainer(
    modifier: Modifier,
    groupInfo: GroupInfo,
    onGroupDetailClick: (id: Long) -> Unit,
    onClickSendFcmButton: (eventId: Long) -> Unit,
    onClickEventMake: (Long) -> Unit,
    onNavigateVote: (eventId: Long) -> Unit,
    onNavigateGallery: (eventId: Long) -> Unit,
) {
    Column(
        modifier = modifier.noRippleClickable(isSingleClick = true) {
            if (groupInfo.status != GroupStatusType.NO_PAST_AND_CURRENT_EVENT) {
                onGroupDetailClick(groupInfo.id)
            }
        },
    ) {
        GroupTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            groupName = groupInfo.name,
            isVisibleNavigation = groupInfo.status != GroupStatusType.NO_PAST_AND_CURRENT_EVENT,
        )

        GroupTag(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            keyword = groupInfo.keyword,
            statusDesc = groupInfo.statusDescription,
        )

        GroupCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 16.dp,
                    start = 46.dp,
                    end = 46.dp,
                ),
            groupInfo = groupInfo,
            onClickEventMake = onClickEventMake,
        )

        GroupStatusType
            .getButtonRes(groupInfo.status)
            ?.let { (iconResId, textResId, clickType) ->
                PicNormalButton(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    iconRes = iconResId,
                    isRippleClickable = true,
                    text = stringResource(textResId),
                    isSingleClick = true,
                    isHaptic = ClickType.Fcm == clickType,
                    onButtonClicked = {
                        when (clickType) {
                            ClickType.Fcm -> onClickSendFcmButton(groupInfo.recentEvent.id)
                            ClickType.Vote -> onNavigateVote(groupInfo.recentEvent.id)
                            ClickType.Gallery -> onNavigateGallery(groupInfo.recentEvent.id)
                        }
                    },
                )
            }

        if (groupInfo.status == GroupStatusType.BEFORE_MY_UPLOAD) {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.group_home_pic_upload_description),
                style = PicTypography.captionNormal12,
                color = Gray60,
            )
        }
    }
}

@Composable
private fun GroupCard(modifier: Modifier, groupInfo: GroupInfo, onClickEventMake: (Long) -> Unit) {
    FlippableBox(
        modifier = modifier,
        frontScreen = {
            GroupHomePhotoCard(
                modifier = Modifier,
                groupInfo = groupInfo,
                backgroundColor = groupInfo.keyword.frontCardBackgroundColor,
                eventName = groupInfo.recentEvent.title,
                content = {
                    FrontCardImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 74.dp, bottom = 96.dp, start = 30.dp, end = 30.dp)
                            .align(Alignment.Center),
                        frameResId = groupInfo.frontImageFrame.frameResId,
                        imageUrl = groupInfo.cardFrontImageUrl,
                        backgroundColor = groupInfo.keyword.frontCardBackgroundColor,
                    )
                },
                onClickEventMake = onClickEventMake,
            )
        },
        backScreen = {
            GroupHomePhotoCard(
                modifier = Modifier,
                groupInfo = groupInfo,
                backgroundColor = groupInfo.keyword.behindCardBackGroundColor,
                eventName = groupInfo.recentEvent.title,
                content = {
                    BackCardImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 74.dp, bottom = 96.dp, start = 30.dp, end = 30.dp)
                            .align(Alignment.Center),
                        cardBackImageList = groupInfo.cardBackImages,
                        backgroundColor = groupInfo.keyword.behindCardBackGroundColor,
                    )
                },
                onClickEventMake = onClickEventMake,
            )
        },
        flipAnimationSpec = tween(
            durationMillis = 700,
            easing = LinearOutSlowInEasing,
        ),
        enableFlip = groupInfo.cardBackImages.isNotEmpty(),
        enableFlipByDrag = true,
    )
}

@Composable
private fun GroupTitle(
    modifier: Modifier,
    groupName: String,
    isVisibleNavigation: Boolean,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Text(
            text = groupName,
            color = Gray80,
            style = PicTypography.headBold24,
        )
        if (isVisibleNavigation) {
            Image(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = stringResource(R.string.detail_page_move),
            )
        }
    }
}

@Composable
private fun GroupTag(
    modifier: Modifier,
    keyword: GroupKeyword,
    statusDesc: String,
) {
    Row(modifier = modifier) {
        PicTag(
            modifier = Modifier.padding(end = 6.dp),
            text = stringResource(id = keyword.tagNameResId),
            iconRes = keyword.symbolResId,
            iconColor = keyword.symbolColor,
        )
        PicTag(
            text = statusDesc,
        )
    }
}

@Composable
private fun FrontCardImage(
    modifier: Modifier,
    imageUrl: String,
    @DrawableRes frameResId: Int,
    backgroundColor: Color,
) {
    Box(
        modifier = modifier.wrapContentSize(),
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.group_main_picture),
            placeholder = ColorPainter(Gray0),
        )
        StableImage(
            modifier = Modifier.aspectRatio(1f),
            drawableResId = frameResId,
            colorFilter = ColorFilter.tint(backgroundColor),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
    }
}

@Composable
private fun BackCardImage(
    modifier: Modifier,
    cardBackImageList: List<CardBackImage>,
    backgroundColor: Color,
) {
    val contentMaxHeight = LocalConfiguration.current.screenHeightDp.dp
    LazyVerticalGrid(
        modifier = modifier
            .wrapContentSize()
            .heightIn(max = contentMaxHeight),
        verticalArrangement = Arrangement.spacedBy(7.46.dp),
        horizontalArrangement = Arrangement.spacedBy(7.46.dp),
        columns = GridCells.Fixed(2),
        userScrollEnabled = false,
    ) {
        items(items = cardBackImageList) { cardBackImage ->
            Box(modifier = Modifier.aspectRatio(1f)) {
                FrontCardImage(
                    modifier = Modifier,
                    imageUrl = cardBackImage.imageUrl,
                    frameResId = cardBackImage.frameType.frameResId,
                    backgroundColor = backgroundColor,
                )
            }
        }
    }
}

@Composable
private fun GroupFloatingButton(
    modifier: Modifier,
    onClickGroupMake: () -> Unit,
    onClickGroupEnter: () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 45f else 0f,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
        label = stringResource(R.string.floating_animate),
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        AnimatedVisibility(
            modifier = Modifier.shadow(5.dp, RoundedCornerShape(16.dp)),
            visible = isExpanded,
        ) {
            FloatingButtonContent(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp),
                    )
                    .padding(16.dp),
                onClickGroupMake = {
                    isExpanded = false
                    onClickGroupMake()
                },
                onClickGroupEnter = {
                    isExpanded = false
                    onClickGroupEnter()
                },
            )
        }

        FloatingActionButton(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { isExpanded = !isExpanded },
            shape = CircleShape,
            containerColor = if (isExpanded) Color.White else Gray80,
            elevation = FloatingActionButtonDefaults.elevation(if (isExpanded) 5.dp else 0.dp),
        ) {
            StableImage(
                modifier = Modifier
                    .rotate(rotationAngle)
                    .padding(16.dp),
                drawableResId = R.drawable.ic_floating_plus,
                colorFilter = ColorFilter.tint(if (isExpanded) Color.Black else Color.White),
                contentDescription = stringResource(R.string.floating_btn),
            )
        }
    }
}

@Composable
private fun FloatingButtonContent(
    modifier: Modifier,
    onClickGroupMake: () -> Unit,
    onClickGroupEnter: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        FloatingItem(
            modifier = Modifier,
            textResId = R.string.floationg_group_enter,
            imageResId = R.drawable.ic_group_enter,
            onClicked = { onClickGroupEnter() },
        )
        FloatingItem(
            modifier = Modifier.padding(top = 16.dp),
            textResId = R.string.floating_group_creation,
            imageResId = R.drawable.ic_group_add,
            onClicked = { onClickGroupMake() },
        )
    }
}

@Composable
private fun FloatingItem(
    modifier: Modifier,
    textResId: Int,
    @DrawableRes imageResId: Int,
    onClicked: () -> Unit,
) {
    Row(
        modifier = modifier.noRippleClickable(isSingleClick = true) { onClicked() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StableImage(
            modifier = Modifier.padding(end = 8.dp),
            drawableResId = imageResId,
            contentDescription = stringResource(R.string.floating_btn_desc),
        )
        Text(
            text = stringResource(id = textResId),
            color = Gray80,
            style = PicTypography.bodyMedium16,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupHomeScreenPreview() {
    GroupHomeScreen(
        modifier = Modifier,
        groupList = ImmutableList(
            listOf(
                GroupInfo(
                    id = 0,
                    name = "그룹 이름",
                    keyword = GroupKeyword.LITTLE_MOIM,
                    statusDescription = "상태 설명",
                    frontImageFrame = PicPhotoFrame.GHOST,
                    cardFrontImageUrl = "https://picsum.photos/200/300",
                    cardBackImages = listOf(
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
                    recentEvent = GroupEvent(
                        id = 0,
                        title = "가빵집 MT",
                        date = "2024.11.01",
                        deadline = "2024.11.01",
                    ),
                    status = GroupStatusType.BEFORE_MY_UPLOAD,
                ),
                GroupInfo(
                    id = 0,
                    name = "그룹 이름",
                    keyword = GroupKeyword.LITTLE_MOIM,
                    statusDescription = "상태 설명",
                    frontImageFrame = PicPhotoFrame.SEXY,
                    cardFrontImageUrl = "https://picsum.photos/200/300",
                    cardBackImages = listOf(
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
                    recentEvent = GroupEvent(
                        id = 0,
                        title = "가빵집 MT",
                        date = "2024.11.01",
                        deadline = "2024.11.01",
                    ),
                    status = GroupStatusType.NO_PAST_AND_CURRENT_EVENT,
                ),
                GroupInfo(
                    id = 0,
                    name = "그룹 이름",
                    keyword = GroupKeyword.SCHOOL,
                    statusDescription = "상태 설명",
                    frontImageFrame = PicPhotoFrame.PLUS,
                    cardFrontImageUrl = "https://picsum.photos/200/300",
                    cardBackImages = listOf(
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
                    recentEvent = GroupEvent(
                        id = 0,
                        title = "가빵집 MT",
                        date = "2024.11.01",
                        deadline = "2024.11.01",
                    ),
                    status = GroupStatusType.NO_PAST_AND_CURRENT_EVENT,
                ),
                GroupInfo(
                    id = 0,
                    name = "그룹 이름",
                    keyword = GroupKeyword.HOBBY,
                    statusDescription = "상태 설명",
                    frontImageFrame = PicPhotoFrame.SNOWMAN,
                    cardFrontImageUrl = "https://picsum.photos/200/300",
                    cardBackImages = listOf(
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
                    recentEvent = GroupEvent(
                        id = 0,
                        title = "가빵집 MT",
                        date = "2024.11.01",
                        deadline = "2024.11.01",
                    ),
                    status = GroupStatusType.NO_PAST_AND_CURRENT_EVENT,
                ),
            ),
        ),
        filterTagList = ImmutableList(
            listOf(
                FilterTag(
                    "전체",
                    null,
                    Gray100,
                    R.string.tag_total,
                    false,
                ),
                FilterTag(
                    "취미",
                    R.drawable.sb_hobby,
                    Malibu,
                    R.string.tag_total,
                    true,
                ),
            ),
        ),
        viewType = ViewType.List,
        onClickGroupDetail = {},
        onClickEventMake = {},
        onClickMyPage = {},
        onClickGroupMake = {},
        onClickGroupEnter = {},
        onClickSendFcmButton = {},
        onNavigateGallery = {},
        onNavigateVote = {},
        onClickFilterTag = {},
        onClickViewType = {},
    )
}
