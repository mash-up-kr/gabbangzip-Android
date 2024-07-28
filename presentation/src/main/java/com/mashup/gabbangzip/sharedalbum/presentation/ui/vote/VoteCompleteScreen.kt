package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton

@Composable
fun VoteCompleteScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        VoteCompleteContent(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f),
        )
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 40.dp),
            text = stringResource(id = R.string.complete),
        )
    }
}

@Composable
private fun VoteCompleteContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.vote_complete_title),
            style = PicTypography.headBold20,
            color = Gray80,
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.vote_complete_subtitle),
            style = PicTypography.bodyMedium14,
            color = Gray60,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .size(240.dp)
                .background(color = Gray60),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun VoteCompleteScreenPreview() {
    VoteCompleteScreen()
}
