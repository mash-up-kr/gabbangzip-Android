package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage

@Composable
fun GroupHomePhotoCard(
    modifier: Modifier,
    groupInfo: GroupInfo,
    backgroundColor: Color,
    content: @Composable BoxScope.() -> Unit,
    eventName: String = "",
    onClickEventMake: (Long) -> Unit,
) {
    GroupPhotoCardContainer(
        modifier = modifier.wrapContentSize(),
        keywordType = groupInfo.keyword,
        backgroundColor = backgroundColor,
    ) {
        CardTopTitle(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 37.dp),
            text = if (groupInfo.status == GroupStatusType.NO_PAST_AND_CURRENT_EVENT ||
                groupInfo.status == GroupStatusType.NO_CURRENT_EVENT
            ) {
                stringResource(id = R.string.intro_event_top_title)
            } else {
                groupInfo.recentEvent.date
            },
        )

        if (groupInfo.status == GroupStatusType.NO_PAST_AND_CURRENT_EVENT ||
            groupInfo.status == GroupStatusType.NO_CURRENT_EVENT
        ) {
            PicNormalButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 19.dp, bottom = 34.dp),
                isRippleClickable = true,
                isSingleClick = true,
                text = stringResource(R.string.event_creation_btn_text),
                onButtonClicked = {
                    onClickEventMake(groupInfo.id)
                },
            )
        }

        content()

        if (groupInfo.status != GroupStatusType.NO_PAST_AND_CURRENT_EVENT &&
            groupInfo.status != GroupStatusType.NO_CURRENT_EVENT
        ) {
            EventTitle(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 31.dp, bottom = 41.dp),
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
private fun GroupPhotoCardContainer(
    modifier: Modifier,
    keywordType: GroupKeyword,
    backgroundColor: Color,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
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
