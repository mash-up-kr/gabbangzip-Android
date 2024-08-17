package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.name

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTextField
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common.GroupCreationScaffold
import com.mashup.gabbangzip.sharedalbum.presentation.utils.hideKeyboardOnOutsideClicked

@Composable
fun GroupCreationNameScreen(
    initialName: String,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: (name: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var name by remember { mutableStateOf(initialName) }
    val buttonEnabled by remember { derivedStateOf { name.isNotBlank() } }
    GroupCreationNameScreen(
        name = name,
        focusManager = focusManager,
        setName = { name = it },
        buttonEnabled = buttonEnabled,
        onBackButtonClicked = onBackButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
    )
}

@Composable
private fun GroupCreationNameScreen(
    name: String,
    focusManager: FocusManager,
    buttonEnabled: Boolean,
    setName: (String) -> Unit,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: (name: String) -> Unit,
) {
    GroupCreationScaffold(
        modifier = Modifier.hideKeyboardOnOutsideClicked(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        topBar = {
            PicBackButtonTopBar(
                modifier = Modifier
                    .background(Gray0Alpha80)
                    .padding(top = 16.dp),
                titleText = stringResource(id = R.string.group_creation_button_name),
                backButtonClicked = {
                    focusManager.clearFocus()
                    onBackButtonClicked()
                },
            )
            PicProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
                level = 1,
                total = 4f,
            )
        },
        bottomFloatingButton = {
            PicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
                text = stringResource(id = R.string.next),
                isRippleClickable = true,
                enable = buttonEnabled,
                onButtonClicked = {
                    focusManager.clearFocus()
                    onNextButtonClicked(name)
                },
            )
        },
        content = {
            GroupCreationNameContent(
                name = name,
                setName = setName,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            )
        },
    )
}

@Composable
private fun GroupCreationNameContent(
    name: String,
    setName: (String) -> Unit,
) {
    Text(
        modifier = Modifier.padding(bottom = 16.dp),
        text = stringResource(id = R.string.group_creation_name_title),
        style = PicTypography.headBold18,
        color = Gray80,
    )
    PicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        onValueChange = setName,
        hint = stringResource(id = R.string.group_creation_name_hint),
        maxLength = 10,
    )
}

@Preview(showBackground = true)
@Composable
fun GroupCreationNameScreenPreview() {
    SharedAlbumTheme {
        GroupCreationNameScreen(
            name = "",
            focusManager = LocalFocusManager.current,
            buttonEnabled = false,
            setName = {},
            onBackButtonClicked = {},
            onNextButtonClicked = {},
        )
    }
}
