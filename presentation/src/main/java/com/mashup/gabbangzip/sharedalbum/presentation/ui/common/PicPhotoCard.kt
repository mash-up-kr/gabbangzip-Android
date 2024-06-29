package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyWord

@Composable
fun PicPhotoCard(
    modifier: Modifier,
    groupInfo: GroupInfo,
) {
    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
            )
            .background(color = GroupKeyWord.getBackgroundColor(groupInfo.keyword)),
    ) {
        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = 24.dp,
                    start = 22.dp,
                ),
            symbol = groupInfo.keyword,
        )

        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 24.dp,
                    end = 22.dp,
                ),
            symbol = groupInfo.keyword,
        )

        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    bottom = 24.dp,
                    start = 22.dp,
                ),
            symbol = groupInfo.keyword,
        )

        KeywordMiniSymbol(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = 24.dp,
                    end = 22.dp,
                ),
            symbol = groupInfo.keyword,
        )

        GroupImage(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            groupInfo = groupInfo,
        )
    }
}

@Composable
private fun KeywordMiniSymbol(modifier: Modifier, symbol: GroupKeyWord) {
    Image(
        modifier = modifier.size(10.dp),
        painter = painterResource(id = GroupKeyWord.getSymbol(symbol)),
        contentDescription = stringResource(R.string.group_symbol),
    )
}

@Composable
private fun GroupImage(modifier: Modifier, groupInfo: GroupInfo) {
    Box(
        modifier = modifier.padding(top = 74.dp, bottom = 96.dp, start = 30.dp, end = 30.dp),
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            model = groupInfo.thumbnailUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.group_main_picture),
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GroupKeyWord.getBackgroundColor(groupInfo.keyword)),
            model = groupInfo.thumbnailFrameUrl,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
    }
}
