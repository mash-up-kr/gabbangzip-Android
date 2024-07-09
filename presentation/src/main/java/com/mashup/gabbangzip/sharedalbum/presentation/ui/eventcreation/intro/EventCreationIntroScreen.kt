package com.mashup.gabbangzip.sharedalbum.presentation.ui.eventcreation.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage

@Composable
fun EventCreationIntroScreen(
    onNextButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    Column {
        PicBackButtonTopBar(
            modifier = Modifier
                .background(Gray0Alpha80)
                .padding(top = 16.dp),
            titleText = stringResource(id = R.string.event_creation),
            backButtonClicked = onBackButtonClicked,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(top = 99.dp),
                text = stringResource(id = R.string.event_creation_title),
                style = PicTypography.headBold20,
                color = Gray80,
                textAlign = TextAlign.Center,
            )
            StableImage(
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 16.dp),
                drawableResId = R.drawable.sb_exercise,
                contentDescription = "",
            )
            Text(
                modifier = Modifier.padding(top = 26.dp),
                text = stringResource(id = R.string.event_creation_description),
                style = PicTypography.bodyMedium14,
                color = Gray60,
            )
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
            text = stringResource(id = R.string.event_creation),
            isRippleClickable = true,
            onButtonClicked = onNextButtonClicked,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EventCreationIntroScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray0),
    ) {
        EventCreationIntroScreen(
            onNextButtonClicked = {},
            onBackButtonClicked = {},
        )
    }
}
