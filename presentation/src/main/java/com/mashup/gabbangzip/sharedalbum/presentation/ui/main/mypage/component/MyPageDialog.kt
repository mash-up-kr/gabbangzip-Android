package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.MyPageUiState

@Composable
fun MyPageDialog(
    dialogState: MyPageUiState.DialogState,
    onDismiss: () -> Unit,
    onLogout: () -> Unit,
    onWithdrawal: () -> Unit,
) {
    when (dialogState) {
        MyPageUiState.DialogState.None -> Unit
        MyPageUiState.DialogState.Logout -> {
            PicDialog(
                titleText = stringResource(id = R.string.dialog_logout_title),
                dismissText = stringResource(id = R.string.cancel),
                confirmText = stringResource(id = R.string.logout),
                onDismiss = onDismiss,
                onConfirm = onLogout,
            )
        }

        MyPageUiState.DialogState.Withdrawal -> {
            PicDialog(
                titleText = stringResource(id = R.string.dialog_withdrawal_title),
                contentText = stringResource(id = R.string.dialog_withdrawal_contents),
                confirmText = stringResource(id = R.string.withdrawal),
                dismissText = stringResource(id = R.string.cancel),
                onDismiss = onDismiss,
                onConfirm = onWithdrawal,
            )
        }
    }
}
