package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.CardBackImage
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.rippleClickable

@Composable
fun PicPhotoCard(
    modifier: Modifier,
    groupInfo: GroupInfo,
    content: @Composable BoxScope.() -> Unit,
    eventName: String = "",
    onCreateEvent: () -> Unit = {},
) {
    PicPhotoCardContainer(
        modifier = modifier,
        keywordType = groupInfo.keyword,
    ) {
        if (groupInfo.status == GroupStatusType.NO_PAST_AND_CURRENT_EVENT) {
            CardTopTitle(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 37.dp),
                text = "이벤트를 만들어보세요",
            )
        }

        if (groupInfo.status == GroupStatusType.NO_CURRENT_EVENT) {
            CardTopTitle(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 37.dp),
                text = groupInfo.recentEventDate,
            )
        }

        if (groupInfo.status == GroupStatusType.NO_PAST_AND_CURRENT_EVENT) {
            PicNormalButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 19.dp, bottom = 34.dp)
                    .rippleClickable { onCreateEvent() },
                text = "이벤트 생성하기",
            )
        }

        content()

        if (groupInfo.status == GroupStatusType.NO_CURRENT_EVENT) {
            EventTitle(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 31.dp),
                text = eventName,
            )
        }
    }
}

@Composable
private fun CardTopTitle(modifier: Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        color = Gray80,
        style = PicTypography.bodyMedium16,
    )
}

@Composable
private fun EventTitle(modifier: Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        color = Gray80,
        style = PicTypography.headBold20,
    )
}

@Composable
fun PicPhotoCardContainer(
    modifier: Modifier,
    keywordType: GroupKeyword,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = keywordType.backgroundColor,
                shape = RoundedCornerShape(20.dp),
            )
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
            ),
    ) {
        content()

        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = 24.dp,
                    start = 22.dp,
                ),
            keyword = keywordType,
        )

        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 24.dp,
                    end = 22.dp,
                ),
            keyword = keywordType,
        )

        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    bottom = 24.dp,
                    start = 22.dp,
                ),
            keyword = keywordType,
        )

        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = 24.dp,
                    end = 22.dp,
                ),
            keyword = keywordType,
        )
    }
}

@Composable
private fun KeywordMiniSymbol(modifier: Modifier, keyword: GroupKeyword) {
    StableImage(
        modifier = modifier.size(10.dp),
        drawableResId = keyword.symbolResId,
        contentDescription = stringResource(R.string.group_symbol),
    )
}

@Composable
fun PicFrontCardImage(
    modifier: Modifier,
    frontImageUrl: String,
    @DrawableRes frameResId: Int,
    backgroundColor: Color = Gray0,
) {
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            model = frontImageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.group_main_picture),
        )
        StableImage(
            modifier = Modifier.matchParentSize(),
            drawableResId = frameResId,
            colorFilter = ColorFilter.tint(backgroundColor),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
    }
}

@Composable
fun PicBackCardImage(
    modifier: Modifier,
    cardBackImageList: List<CardBackImage>,
    backgroundColor: Color,
) {
    LazyVerticalGrid(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(7.46.dp),
        horizontalArrangement = Arrangement.spacedBy(7.46.dp),
        columns = GridCells.Fixed(2),
        userScrollEnabled = false,
    ) {
        items(cardBackImageList) { cardBackImage ->
            Box(modifier = Modifier.aspectRatio(1f)) {
                AsyncImage(
                    modifier = Modifier.matchParentSize(),
                    model = cardBackImage.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                StableImage(
                    modifier = Modifier.matchParentSize(),
                    drawableResId = cardBackImage.frameType.frameResId,
                    colorFilter = ColorFilter.tint(backgroundColor),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PicFrontPhotoCardPreview() {
    PicPhotoCard(
        modifier = Modifier.wrapContentSize(),
        groupInfo = GroupInfo(
            id = 1,
            status = GroupStatusType.NO_PAST_AND_CURRENT_EVENT,
            name = "Group Name",
            keyword = GroupKeyword.CREW,
            statusDescription = "status description",
            cardFrontImageUrl = "https://picsum.photos/200/300",
            frontImageFrame = PicPhotoFrame.HAMBURGER,
            recentEventDate = "2021.10.10",
            cardBackImages = listOf(
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.HAMBURGER,
                ),
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.SNOWMAN,
                ),
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.GHOST,
                ),
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.PLUS,
                ),
            ),
        ),
        eventName = "이벤트 이름",
        content = {
            PicFrontCardImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 74.dp, bottom = 96.dp, start = 30.dp, end = 30.dp)
                    .align(Alignment.Center),
                frontImageUrl = "https://picsum.photos/200/300",
                frameResId = PicPhotoFrame.HAMBURGER.frameResId,
                backgroundColor = GroupKeyword.CREW.backgroundColor,
            )
        },
    )
}

@Preview
@Composable
private fun PicBackPhotoCardPreview() {
    PicPhotoCard(
        modifier = Modifier.wrapContentSize(),
        groupInfo = GroupInfo(
            id = 1,
            status = GroupStatusType.NO_PAST_AND_CURRENT_EVENT,
            name = "Group Name",
            keyword = GroupKeyword.CREW,
            statusDescription = "status description",
            cardFrontImageUrl = "https://picsum.photos/200/300",
            frontImageFrame = PicPhotoFrame.HAMBURGER,
            recentEventDate = "2021.10.10",
            cardBackImages = listOf(
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.HAMBURGER,
                ),
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.SNOWMAN,
                ),
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.GHOST,
                ),
                CardBackImage(
                    imageUrl = "https://picsum.photos/200/300",
                    frameType = PicPhotoFrame.PLUS,
                ),
            ),
        ),
        eventName = "이벤트 이름",
        content = {
            PicBackCardImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 74.dp, bottom = 96.dp, start = 30.dp, end = 30.dp)
                    .align(Alignment.Center),
                cardBackImageList = listOf(
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.HAMBURGER,
                    ),
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.SNOWMAN,
                    ),
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.GHOST,
                    ),
                    CardBackImage(
                        imageUrl = "https://picsum.photos/200/300",
                        frameType = PicPhotoFrame.PLUS,
                    ),
                ),
                backgroundColor = GroupKeyword.CREW.backgroundColor,
            )
        },
    )
}

@Preview
@Composable
private fun PicFrontCardImagePreview() {
    PicFrontCardImage(
        modifier = Modifier.fillMaxSize(),
        frameResId = PicPhotoFrame.PLUS.frameResId,
        frontImageUrl = "https://picsum.photos/200/300",
    )
}

@Preview
@Composable
private fun PicBackCardImagePreview() {
    PicBackCardImage(
        modifier = Modifier.wrapContentSize(),
        cardBackImageList = listOf(
            CardBackImage(
                imageUrl = "https://picsum.photos/200/300",
                frameType = PicPhotoFrame.HAMBURGER,
            ),
            CardBackImage(
                imageUrl = "https://picsum.photos/200/300",
                frameType = PicPhotoFrame.SNOWMAN,
            ),
            CardBackImage(
                imageUrl = "https://picsum.photos/200/300",
                frameType = PicPhotoFrame.GHOST,
            ),
            CardBackImage(
                imageUrl = "https://picsum.photos/200/300",
                frameType = PicPhotoFrame.PLUS,
            ),
        ),
        backgroundColor = GroupKeyword.CREW.backgroundColor,
    )
}

@Preview
@Composable
private fun PicPhotoCardContainerPreview() {
    PicPhotoCardContainer(
        modifier = Modifier.fillMaxSize(),
        keywordType = GroupKeyword.CREW,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green),
        )
    }
}
