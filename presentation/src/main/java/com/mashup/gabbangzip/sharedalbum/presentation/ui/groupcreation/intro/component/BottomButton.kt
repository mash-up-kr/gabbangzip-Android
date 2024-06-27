package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.intro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.utils.platformRippleClickable

@Composable
fun BottomButton(
    modifier: Modifier = Modifier,
    descriptionText: String,
    buttonText: String,
    onClickNextButton: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 8.dp),
            text = descriptionText,
            textAlign = TextAlign.Center,
            color = Gray60,
            style = PicTypography.bodyMedium14,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(Gray80)
                .platformRippleClickable(onClick = onClickNextButton),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 20.dp),
                text = buttonText,
                textAlign = TextAlign.Center,
                style = PicTypography.bodyMedium17,
                color = Gray0,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomButtonPreview() {
    BottomButton(
        descriptionText = "초대 링크를 받으셨나요?\n링크를 눌러 바로 그룹에 들어갈 수 있어요.",
        buttonText = "그룹 만들기",
        onClickNextButton = {},
    )
}
