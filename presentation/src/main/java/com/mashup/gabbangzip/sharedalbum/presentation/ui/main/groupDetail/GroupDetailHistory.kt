package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
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
@Preview(showBackground = true)
private fun EmptyHistoryPreview() {
    SharedAlbumTheme {
        EventHistoryEmptyContent(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
