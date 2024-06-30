package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
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
    var name by remember { mutableStateOf("") }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                level = 1
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = stringResource(id = R.string.group_creation_name_title),
                style = PicTypography.headBold18,
                color = Gray80,
            )
            PicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                hint = stringResource(id = R.string.group_creation_name_hint),
                maxLength = 10,
            )
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.group_creation_button_next),
            isRippleClickable = true,
            enable = name.isNotBlank(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupCreationNameScreenPreview() {
    SharedAlbumTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray0),
        ) {
            GroupCreationNameScreen(
                onNextButtonClicked = {},
            )
        }
    }
}
