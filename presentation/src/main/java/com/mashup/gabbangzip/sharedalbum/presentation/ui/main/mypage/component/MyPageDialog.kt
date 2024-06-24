package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.platformRippleClickable

@Composable
fun MyPageDialog(
    modifier: Modifier = Modifier,
    titleText: String,
    contentsText: String,
    confirmText: String,
    dismissText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .width(330.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(Gray0)
                .padding(top = 30.dp, bottom = 18.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = titleText,
                style = PicTypography.headBold16,
                color = Gray80,
            )
            if (contentsText.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 10.dp),
                    text = contentsText,
                    textAlign = TextAlign.Center,
                    style = PicTypography.bodyMedium14,
                    color = Gray60,
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 26.dp),
            ) {
                DialogButton(
                    modifier = Modifier
                        .width(145.dp)
                        .wrapContentHeight()
                        .platformRippleClickable(onClick = onDismiss),
                    text = dismissText,
                    backgroundColor = Gray40,
                    textColor = Gray80,
                )
                DialogButton(
                    modifier = Modifier
                        .width(145.dp)
                        .wrapContentHeight()
                        .platformRippleClickable(onClick = onConfirm),
                    text = confirmText,
                    backgroundColor = Gray80,
                    textColor = Gray0,
                )
            }
        }
    }
}

@Composable
fun DialogButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    textColor: Color,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp),
            text = text,
            textAlign = TextAlign.Center,
            color = textColor,
            style = PicTypography.bodyMedium14,
        )
    }
}

@Preview
@Composable
fun MyPageDialogPreview() {
    MyPageDialog(
        titleText = "로그아웃 하시겠어요?",
        contentsText = "",
        confirmText = "로그아웃",
        dismissText = "취소",
        onDismiss = {},
        onConfirm = {},
    )
}

@Preview
@Composable
fun MyPageDialogPreview2() {
    MyPageDialog(
        titleText = "로그아웃 하시겠어요?",
        contentsText = "탈퇴 시 그룹, 활동 내역이\n삭제되며, 복구되지 않습니다.",
        confirmText = "로그아웃",
        dismissText = "취소",
        onDismiss = {},
        onConfirm = {},
    )
}
