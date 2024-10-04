package com.mashup.gabbangzip.sharedalbum.presentation.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicHorizontalDotIndicator
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    pageResourceIdList: List<Int>,
    onClickStart: () -> Unit,
) {
    val pagerState = rememberPagerState { pageResourceIdList.size }
    val pageDescription = stringResource(id = R.string.page_description)

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
        ) { page ->
            StableImage(
                modifier = Modifier.fillMaxSize(),
                drawableResId = pageResourceIdList[page],
                contentDescription = pageDescription.format(page),
                contentScale = ContentScale.FillWidth,
            )
        }
        Column(
            modifier = Modifier
                .padding(bottom = 16.dp, start = 21.dp, end = 21.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PicHorizontalDotIndicator(
                circleSize = 8.dp,
                selectedColor = Gray80,
                unselectedColor = Gray50,
                totalPage = pagerState.pageCount,
                currentPage = pagerState.currentPage + 1,
                indicatorSpacing = 10.dp,
            )
            PicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                text = stringResource(id = R.string.start),
                onButtonClicked = onClickStart,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreen(
        pageResourceIdList = listOf(),
        onClickStart = {},
    )
}
