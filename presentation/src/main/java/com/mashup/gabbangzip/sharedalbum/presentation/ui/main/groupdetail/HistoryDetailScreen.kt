package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import android.graphics.Bitmap
import android.graphics.Picture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicCroppedPhoto
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicPhotoCardFrame
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.CardBackImage
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.captureIntoCanvas
import com.mashup.gabbangzip.sharedalbum.presentation.utils.createBitmap

@Composable
fun HistoryDetailScreen(
    modifier: Modifier,
    groupName: String,
    keyword: GroupKeyword,
    item: HistoryItem,
    onClickBackButton: () -> Unit,
    onClickShareButton: (Bitmap) -> Unit,
) {
    val picture = remember { Picture() }
    Column(modifier = modifier) {
        PicBackButtonTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            isLightMode = false,
            titleText = groupName,
            titleAlign = PicTopBarTitleAlign.LEFT,
            backButtonClicked = onClickBackButton,
        )
        Text(
            modifier = Modifier.padding(start = 46.dp),
            text = "2024.05.24",
            style = PicTypography.bodyMedium16,
            color = Color(0xFFB3B3B3),
        )
        HistoryPhotoCard(
            modifier = Modifier
                .weight(1f)
                .captureIntoCanvas(picture),
            keyword = keyword,
            item = item,
        )
        PicNormalButton(
            modifier = Modifier
                .padding(bottom = 19.dp)
                .align(Alignment.CenterHorizontally),
            iconRes = R.drawable.ic_share,
            onButtonClicked = {
                val bitmap = picture.createBitmap()
                onClickShareButton(bitmap)
            },
        )
    }
}

@Composable
private fun HistoryPhotoCard(
    modifier: Modifier = Modifier,
    keyword: GroupKeyword,
    item: HistoryItem,
) {
    Box(modifier = modifier.wrapContentSize()) {
        PicPhotoCardFrame(
            modifier = Modifier.matchParentSize(),
            keywordType = keyword,
            frameResId = PicPhotoFrame.getTypeByKeyword(keyword.name).frameResId,
            content = {},
        )
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 24.dp),
                text = item.date,
                style = PicTypography.bodyMedium16,
            )
            GridPhotoSection(
                backgroundColor = keyword.frontCardBackgroundColor,
                images = item.images,
            )
            Text(
                modifier = Modifier
                    .padding(top = 28.dp, bottom = 48.dp),
                text = item.title,
                style = PicTypography.headBold20,
            )
        }
    }
}

@Composable
private fun GridPhotoSection(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    images: ImmutableList<CardBackImage>,
) {
    LazyVerticalGrid(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        columns = GridCells.Fixed(2),
        userScrollEnabled = false,
    ) {
        items(items = images) { image ->
            PicCroppedPhoto(
                modifier = Modifier.aspectRatio(1f),
                imageUrl = image.imageUrl,
                frameResId = image.frameType.frameResId,
                backgroundColor = backgroundColor,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HistoryDetailScreenPreview() {
    SharedAlbumTheme {
        HistoryDetailScreen(
            modifier = Modifier,
            groupName = "가빵집",
            keyword = GroupKeyword.CREW,
            item = HistoryItem(
                id = 0,
                title = "MTT",
                date = "2024.01.11",
                images = ImmutableList(
                    listOf(
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
                ),
            ),
            onClickBackButton = {},
            onClickShareButton = {},
        )
    }
}
