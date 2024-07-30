package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage

@Composable
fun PicPhotoCard(
    modifier: Modifier,
    groupInfo: GroupInfo,
) {
    PicPhotoCardFrame(
        modifier = modifier,
        keywordType = groupInfo.keyword,
        frameResId = groupInfo.frontImageFrame.frameResId,
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            model = groupInfo.cardFrontImageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.group_main_picture),
        )
    }
}

@Composable
fun PicPhotoCardFrame(
    modifier: Modifier,
    keywordType: GroupKeyword,
    @DrawableRes frameResId: Int,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = keywordType.frontCardBackgroundColor,
                shape = RoundedCornerShape(20.dp),
            )
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
            ),
    ) {
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

        PicCroppedImageContainer(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 74.dp, bottom = 96.dp, start = 30.dp, end = 30.dp)
                .align(Alignment.Center),
            frameResId = frameResId,
            backgroundColor = keywordType.frontCardBackgroundColor,
            content = content,
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
private fun PicCroppedImageContainer(
    modifier: Modifier,
    @DrawableRes frameResId: Int,
    backgroundColor: Color,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        content()
        StableImage(
            modifier = Modifier.fillMaxSize(),
            drawableResId = frameResId,
            colorFilter = ColorFilter.tint(backgroundColor),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
    }
}

@Composable
fun PicCroppedPhoto(
    modifier: Modifier,
    imageUrl: String,
    @DrawableRes frameResId: Int,
    backgroundColor: Color = Gray0,
) {
    PicCroppedImageContainer(
        modifier = modifier,
        frameResId = frameResId,
        backgroundColor = backgroundColor,
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.group_main_picture),
        )
    }
}

@Preview
@Composable
private fun PicCroppedPhotoPreview() {
    PicCroppedPhoto(
        modifier = Modifier.fillMaxSize(),
        frameResId = PicPhotoFrame.PLUS.frameResId,
        imageUrl = "https://picsum.photos/200/300",
    )
}

@Preview
@Composable
private fun PicPhotoCardFramePreview() {
    PicPhotoCardFrame(
        modifier = Modifier.fillMaxSize(),
        keywordType = GroupKeyword.CREW,
        frameResId = PicPhotoFrame.HAMBURGER.frameResId,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green),
        )
    }
}
