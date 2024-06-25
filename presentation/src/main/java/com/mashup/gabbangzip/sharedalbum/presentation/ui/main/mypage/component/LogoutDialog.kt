package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.gabbangzip.sharedalbum.presentation.R

@Composable
fun LogoutDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    MyPageBasicDialog(
        titleText = stringResource(id = R.string.dialog_logout_title),
        contentsText = "",
        confirmText = stringResource(id = R.string.logout),
        dismissText = stringResource(id = R.string.cancel),
        onDismiss = onDismiss,
        onConfirm = onConfirm,
    )
}

@Preview
@Composable
fun LogoutDialogPreview() {
    LogoutDialog(
        onDismiss = {},
        onConfirm = {},
    )
}
