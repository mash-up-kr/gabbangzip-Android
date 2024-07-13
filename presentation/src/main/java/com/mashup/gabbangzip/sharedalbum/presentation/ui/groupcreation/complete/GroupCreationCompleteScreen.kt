package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTextOnlyTopBar

@Composable
fun GroupCreationCompleteScreen(
    onNextButtonClicked: () -> Unit,
    onCopyLinkButton: () -> Unit,
) {
    Column {
        PicTextOnlyTopBar(
            modifier = Modifier
                .background(Gray0Alpha80)
                .padding(top = 21.dp),
            titleText = "완료",
        )
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PicProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
                level = 4,
                total = 4f,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 16.dp),
                text = "그룹에 친구들을 추가하고\n추억을 함께 PIC 해보세요",
                style = PicTypography.headBold18,
                color = Gray80,
                textAlign = TextAlign.Center,
            )
            Box( // Todo : 프레임 카드 만들어넣기
                modifier = Modifier
                    .size(310.dp, 420.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.Cyan),
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 8.dp),
                text = "그룹원은 최대 6명까지 초대 가능해요.",
                style = PicTypography.bodyMedium14,
                color = Gray60,
                textAlign = TextAlign.Center,
            )
            PicNormalButton(
                text = "링크 복사",
                isRippleClickable = true,
                backgroundColor = Gray40,
                contentColor = Gray80,
                iconRes = R.drawable.ic_link,
                onButtonClicked = onCopyLinkButton,
            )
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
            text = "완료",
            isRippleClickable = true,
            onButtonClicked = onNextButtonClicked,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupCreationCompleteScreenPreview() {
    GroupCreationCompleteScreen(
        onNextButtonClicked = { },
        onCopyLinkButton = { },
    )
}
