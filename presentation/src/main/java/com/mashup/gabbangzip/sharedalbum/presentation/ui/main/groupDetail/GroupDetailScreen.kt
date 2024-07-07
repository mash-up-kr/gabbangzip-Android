package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon

@Composable
fun GroupDetailScreen(
    onClickGroupMemberButton: () -> Unit,
    onClickBackButton: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        PicBackButtonTopBar(
            modifier = Modifier.fillMaxWidth(),
            titleText = "그룹 이름",
            titleAlign = PicTopBarTitleAlign.LEFT,
            backButtonClicked = onClickBackButton,
            rightIcon1 = PicTopBarIcon.PLUS,
            rightIcon2 = PicTopBarIcon.GROUP_MEMBER,
            rightIcon1Clicked = { /* TODO */ },
            rightIcon2Clicked = onClickGroupMemberButton,
        )
        GroupDetailScreenContent()
    }
}

@Composable
private fun GroupDetailScreenContent(
    modifier: Modifier = Modifier,
) {

}

@Composable
private fun GroupDetailEmptyContent(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.change_shape))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "이벤트를 생성하고\n함께한 순간을 공유해요!",
            style = PicTypography.headBold20,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        LottieAnimation(
            modifier = Modifier.size(164.dp),
            composition = composition,
            progress = { progress },
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "공유한 사진을 함께 PIC하면 네컷사진을 만들 수 있어요.",
            style = PicTypography.textNormal14,
            color = Gray60,
        )
        Spacer(modifier = Modifier.weight(3.7f))
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 30.dp),
            text = "다음",
            isRippleClickable = true,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun EmptyScreenPreview() {
    SharedAlbumTheme {
        GroupDetailEmptyContent(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
