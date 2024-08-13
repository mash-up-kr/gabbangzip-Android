package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicCroppedPhoto
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.preview.EventHistoryProvider
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.CardBackImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun EventHistoryContainer(
    modifier: Modifier = Modifier,
    history: List<HistoryItem>,
    onClickHistoryItem: (HistoryItem) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.group_detail_history_title),
            style = PicTypography.headBold18,
            color = Gray80,
        )
        if (history.isEmpty()) {
            EventHistoryEmptyContent(
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            EventHistoryGridContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                history = history,
                onClickHistoryItem = onClickHistoryItem,
            )
        }
    }
}

@Composable
private fun EventHistoryEmptyContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        StableImage(
            drawableResId = R.drawable.ic_empty_history,
            contentDescription = stringResource(id = R.string.group_detail_empty_history_image_description),
        )
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 45.dp),
            text = stringResource(id = R.string.group_detail_empty_history_label),
            style = PicTypography.textNormal14,
            color = Gray60,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun EventHistoryGridContent(
    modifier: Modifier = Modifier,
    history: List<HistoryItem>,
    onClickHistoryItem: (HistoryItem) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 40.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(history) { item ->
            EventHistoryItemContainer(
                item = item,
                onClickHistoryItem = onClickHistoryItem,
            )
        }
    }
}

@Composable
private fun EventHistoryItemContainer(
    modifier: Modifier = Modifier,
    item: HistoryItem,
    onClickHistoryItem: (HistoryItem) -> Unit,
) {
    Column(
        modifier = modifier
            .noRippleClickable {
                onClickHistoryItem(item)
            },
    ) {
        HistoryThumbnail(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            backgroundColor = Gray60,
            cardBackImageList = item.images,
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = item.title,
            style = PicTypography.headBold18,
            color = Gray80,
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = item.date,
            style = PicTypography.captionNormal12,
            color = Gray60,
        )
    }
}

@Composable
private fun HistoryThumbnail(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    cardBackImageList: ImmutableList<CardBackImage>,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = backgroundColor)
            .padding(10.dp),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            columns = GridCells.Fixed(2),
            userScrollEnabled = false,
        ) {
            items(items = cardBackImageList) { cardBackImage ->
                PicCroppedPhoto(
                    modifier = Modifier.aspectRatio(1f),
                    imageUrl = cardBackImage.imageUrl,
                    frameResId = cardBackImage.frameType.frameResId,
                    backgroundColor = backgroundColor,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EventHistoryContainerPreview(
    @PreviewParameter(EventHistoryProvider::class) history: List<HistoryItem>,
) {
    EventHistoryContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        history = history,
        onClickHistoryItem = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun EventHistoryItemPreview() {
    EventHistoryItemContainer(
        item = HistoryItem(
            title = "가빵집 MT",
            date = "2024.11.03",
        ),
        onClickHistoryItem = {},
    )
}

@Composable
@Preview(showBackground = true)
private fun EmptyHistoryPreview() {
    EventHistoryEmptyContent(
        modifier = Modifier.fillMaxWidth(),
    )
}
