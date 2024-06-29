package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Cultured
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme

@Composable
fun PicDialog(
    modifier: Modifier = Modifier,
    titleText: String,
    contentText: String = "",
    dismissText: String,
    confirmText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Gray0)
                .padding(top = 30.dp, bottom = 18.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.width(298.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = titleText,
                    textAlign = TextAlign.Center,
                    style = PicTypography.headBold16,
                    color = Gray80,
                )
                if (contentText.isNotEmpty()) {
                    Text(
                        text = contentText,
                        textAlign = TextAlign.Center,
                        style = PicTypography.bodyMedium14,
                        color = Gray60,
                    )
                }
            }
            Row(
                modifier = Modifier.padding(top = 26.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PicDialogButton(
                    modifier = Modifier.width(145.dp),
                    text = dismissText,
                    isRippleClickable = true,
                    backgroundColor = Cultured,
                    contentColor = Gray80,
                    onButtonClicked = onDismiss,
                )
                PicDialogButton(
                    modifier = Modifier.width(145.dp),
                    text = confirmText,
                    isRippleClickable = true,
                    onButtonClicked = onConfirm,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PicDialogPreview() {
    SharedAlbumTheme {
        PicDialog(
            titleText = "나가실건가요?",
            contentText = "페이지를 나가면\n작성중인 내용이 삭제돼요.",
            dismissText = "나가기",
            confirmText = "계속 작성하기",
            onDismiss = {},
            onConfirm = {},
        )
    }
}
