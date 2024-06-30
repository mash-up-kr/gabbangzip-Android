package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTextField

@Composable
fun GroupCreationNameScreen(
    onNextButtonClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            }
            .padding(horizontal = 16.dp)
            .imePadding(),
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            PicProgressBar(
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                level = 1
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "그룹의 이름은 무엇인가요?",
                style = PicTypography.headBold18,
                color = Gray80,
            )
            PicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                hint = "최대 10자 까지 입력 가능해요.",
                maxLength = 10,
            )
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = "다음",
            isRippleClickable = true,
            enable = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupCreationNameScreenPreview() {
    SharedAlbumTheme {
        GroupCreationNameScreen(
            onNextButtonClicked = {}
        )
    }
}
