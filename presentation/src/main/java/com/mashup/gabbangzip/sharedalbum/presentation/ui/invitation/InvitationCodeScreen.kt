package com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTextField

@Composable
fun InvitationCodeScreen(
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var input by remember { mutableStateOf("") }
    val isButtonEnabled by remember { derivedStateOf { input.isNotBlank() } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray0),
    ) {
        PicBackButtonTopBar(
            titleText = "그룹 들어가기",
            backButtonClicked = onBackButtonClicked,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                text = "초대코드를 입력해주세요",
                style = PicTypography.headBold18,
                color = Gray80,
            )
            PicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = input,
                onValueChange = { input = it },
                hint = "예) A12B0EHQ",
                maxLength = 10,
            )
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
            text = "다음",
            enable = isButtonEnabled,
            onButtonClicked = {
                focusManager.clearFocus()
                onNextButtonClicked()
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun InvitationCodeScreenPreview() {
    InvitationCodeScreen(
        onBackButtonClicked = {},
        onNextButtonClicked = {},
    )
}
