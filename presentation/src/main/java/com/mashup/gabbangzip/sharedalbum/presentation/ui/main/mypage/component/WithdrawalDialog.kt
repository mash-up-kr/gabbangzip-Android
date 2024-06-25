package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.gabbangzip.sharedalbum.presentation.R

@Composable
fun WithdrawalDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    MyPageBasicDialog(
        titleText = stringResource(id = R.string.dialog_withdrawal_title),
        contentsText = stringResource(id = R.string.dialog_withdrawal_contents),
        confirmText = stringResource(id = R.string.withdrawal),
        dismissText = stringResource(id = R.string.cancel),
        onDismiss = onDismiss,
        onConfirm = onConfirm,
    )
}

@Preview
@Composable
fun WithdrawalDialogPreview() {
    WithdrawalDialog(
        onDismiss = {},
        onConfirm = {},
    )
}
