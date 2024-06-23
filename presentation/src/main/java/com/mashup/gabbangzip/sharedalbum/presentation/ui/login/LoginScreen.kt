package com.mashup.gabbangzip.sharedalbum.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.KakaoOnPrimaryColor
import com.mashup.gabbangzip.sharedalbum.presentation.theme.KakaoPrimaryColor
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.theme.pretendard

@Composable
fun LoginScreen(onClickLoginButton: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray0)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "PIC.",
            fontSize = 48.sp,
            color = Gray80,
            fontFamily = pretendard,
            fontWeight = FontWeight.Black,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "우리가 픽! 하는\n우리끼리 네컷앨범",
            fontSize = 22.sp,
            color = Gray80,
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            letterSpacing = (-0.02).em,
            lineHeight = 27.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(0.4f))
        PicIntroLottie()
        Spacer(modifier = Modifier.weight(1f))
        KakaoLoginButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            onClick = { onClickLoginButton() },
        )
    }
}

@Composable
fun PicIntroLottie(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("lottie_login_fit.lottie"),
    )

    Box(modifier = modifier) {
        LottieAnimation(composition)
    }
}

@Composable
fun KakaoLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = KakaoPrimaryColor)
            .clickable { onClick() }
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_kakao),
            contentDescription = "카카오 로그인 아이콘",
            tint = KakaoOnPrimaryColor,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "카카오 로그인",
            style = PicTypography.bodyMedium16,
            color = KakaoOnPrimaryColor,
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    SharedAlbumTheme {
        LoginScreen(onClickLoginButton = {})
    }
}
