package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.pretendard
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton

@Composable
fun GroupCreationIntroScreen(
    onClickEnterByCodeButton: () -> Unit,
    onClickNextButton: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.change_shape))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 84.dp),
            text = stringResource(id = R.string.group_creation_title),
            textAlign = TextAlign.Center,
            color = Gray80,
            letterSpacing = (0.02).em,
            lineHeight = (28.8).sp,
            fontSize = 24.sp,
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            LottieAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Center)
                    .padding(horizontal = 114.dp),
                composition = composition,
                progress = { progress },
            )
        }
        Text(
            modifier = Modifier.wrapContentSize(),
            text = stringResource(id = R.string.group_creation_button_description),
            textAlign = TextAlign.Center,
            color = Gray60,
            letterSpacing = (0.02).em,
            lineHeight = (16.8).sp,
            style = PicTypography.bodyMedium14,
        )
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, start = 21.dp, end = 21.dp),
            text = stringResource(id = R.string.group_creation_button_code),
            backgroundColor = Gray40,
            contentColor = Gray80,
            onButtonClicked = onClickEnterByCodeButton,
        )
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 12.dp, start = 21.dp, end = 21.dp, bottom = 16.dp),
            text = stringResource(id = R.string.four_group_creation_button_name),
            isRippleClickable = true,
            onButtonClicked = onClickNextButton,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupCreationIntroScreenPreview() {
    GroupCreationIntroScreen(
        onClickEnterByCodeButton = {},
        onClickNextButton = {},
    )
}
