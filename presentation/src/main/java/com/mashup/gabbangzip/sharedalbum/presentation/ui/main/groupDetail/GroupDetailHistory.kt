package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage

@Composable
fun EventHistoryContainer(
    modifier: Modifier = Modifier,
    history: ImmutableList<HistoryItem>,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "우리들의 인생네컷",
            style = PicTypography.headBold18,
            color = Gray80,
        )
        if (history.isEmpty()) {
            EventHistoryEmptyContent(modifier = modifier)
        } else {
            EventHistoryGridContent(
                modifier = modifier,
                history = history,
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
private fun EventHistoryGridContent(
    modifier: Modifier = Modifier,
    history: ImmutableList<HistoryItem>,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(history) { item ->
            EventHistoryItemContainer(
                item = item,
            )
        }
    }
}


@Composable
private fun EventHistoryItemContainer(
    modifier: Modifier = Modifier,
    item: HistoryItem,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(color = Gray60),
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

@Preview(showBackground = true)
@Composable
private fun EventHistoryGridPreview() {
    EventHistoryGridContent(
        history = ImmutableList(
            listOf(
                HistoryItem(
                    title = "가빵집 MT",
                    date = "2024.11.03",
                ),
                HistoryItem(
                    title = "가빵집 MT",
                    date = "2024.11.03",
                ),
                HistoryItem(
                    title = "가빵집 MT",
                    date = "2024.11.03",
                ),
                HistoryItem(
                    title = "가빵집 MT",
                    date = "2024.11.03",
                ),
                HistoryItem(
                    title = "가빵집 MT",
                    date = "2024.11.03",
                ),
            ),
        ),
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
    )
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
