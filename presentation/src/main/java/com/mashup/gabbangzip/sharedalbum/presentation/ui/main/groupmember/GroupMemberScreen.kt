package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicNormalButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.Member
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun GroupMemberScreen(
    innerPadding: PaddingValues,
    onClickBackButton: () -> Unit,
    onSnackbarRequired: (type: PicSnackbarType, message: String) -> Unit,
    navigateToGroupHome: () -> Unit,
    viewModel: GroupMemberViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val copyLinkMessage = stringResource(id = R.string.button_copy_code_message)
    val invitationMessage = stringResource(id = R.string.invitation_message)
    val playStoreUrl = stringResource(id = R.string.play_store_url)
    val appStoreUrl = stringResource(id = R.string.app_store_url)

    var showExitDialog by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (showExitDialog) {
        PicDialog(
            titleText = stringResource(id = R.string.group_withdraw_dialog_title),
            contentText = stringResource(id = R.string.group_withdraw_dialog_content),
            dismissText = stringResource(id = R.string.group_withdraw),
            confirmText = stringResource(id = R.string.group_withdraw_dialog_dismiss),
            onDismiss = {
                showExitDialog = false
                viewModel.withdrawGroup(
                    onSuccess = { navigateToGroupHome() },
                )
            },
            onConfirm = { showExitDialog = false },
            onDismissRequest = { showExitDialog = false },
        )
    }

    GroupMemberScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = state,
        onClickBackButton = onClickBackButton,
        onClickCopyButton = {
            clipboardManager.setText(
                AnnotatedString(
                    invitationMessage.format(playStoreUrl, appStoreUrl, state.invitationCode),
                ),
            )
            onSnackbarRequired(PicSnackbarType.CHECK, copyLinkMessage)
        },
        onClickWithdrawButton = { showExitDialog = true },
    )

    LaunchedEffect(null) {
        viewModel.event.collect {
            when (it) {
                is GroupMemberEvent.SuccessWithdrawGroup -> Unit
                is GroupMemberEvent.FailureWithdrawGroup -> {
                    onSnackbarRequired(PicSnackbarType.WARNING, getString(context, it.message))
                }
            }
        }
    }
}

@Composable
private fun GroupMemberScreen(
    modifier: Modifier = Modifier,
    state: GroupMemberUiState,
    onClickBackButton: () -> Unit,
    onClickCopyButton: () -> Unit,
    onClickWithdrawButton: () -> Unit,
) {
    Box(modifier = modifier) {
        Column {
            PicBackButtonTopBar(
                modifier = Modifier.padding(top = 16.dp),
                titleText = stringResource(id = R.string.group_member_list_title),
                backButtonClicked = onClickBackButton,
            )
            GroupMemberScreenContent(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                onClickCopyButton = onClickCopyButton,
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .noRippleClickable { onClickWithdrawButton() },
            text = stringResource(id = R.string.group_withdraw),
            style = PicTypography.bodyMedium14,
            color = Gray60,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
        )
    }
}

@Composable
private fun GroupMemberScreenContent(
    modifier: Modifier = Modifier,
    state: GroupMemberUiState,
    onClickCopyButton: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState),
    ) {
        GroupMemberList(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 280.dp),
            type = state.keyWord,
            list = state.members,
        )
        InvitationSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            isEnabled = state.isFull.not(),
            onButtonClick = onClickCopyButton,
        )
    }
}

@Composable
private fun GroupMemberList(
    modifier: Modifier = Modifier,
    type: GroupKeyword,
    list: ImmutableList<Member>,
) {
    Column(
        modifier = modifier,
    ) {
        list.forEach { member ->
            GroupMemberItem(
                type = type,
                member = member,
            )
        }
    }
}

@Composable
private fun GroupMemberItem(
    modifier: Modifier = Modifier,
    type: GroupKeyword,
    member: Member,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(24.dp))
        Image(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(id = type.symbolResId),
            contentDescription = type.name,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = member.name,
            style = PicTypography.bodyMedium16,
            color = Gray80,
        )
    }
}

@Composable
private fun InvitationSection(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(
                if (isEnabled) {
                    R.string.group_add_more_member
                } else {
                    R.string.group_maximum_count
                },
            ),
            style = PicTypography.bodyMedium14,
            color = Gray60,
        )
        Spacer(modifier = Modifier.height(8.dp))
        PicNormalButton(
            text = stringResource(id = R.string.button_copy_link),
            iconRes = R.drawable.ic_link,
            enable = isEnabled,
            onButtonClicked = onButtonClick,
        )
    }
}

@Composable
@Preview
private fun GroupMemberScreenPreview(
    @PreviewParameter(GroupMemberProvider::class) state: GroupMemberUiState,
) {
    GroupMemberScreen(
        modifier = Modifier,
        state = state,
        onClickBackButton = {},
        onClickCopyButton = {},
        onClickWithdrawButton = {},
    )
}
