package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.GroupItemNormal
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.GroupTitle
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.MyPageDialog
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.component.UserContainer
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.mypage.viewmodel.MyPageViewModel

private const val TAG = "MyPageScreen"

@Composable
fun MyPageScreen(
    innerPadding: PaddingValues,
    onClickBack: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    navigateLoginAndFinish: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val versionName = remember {
        runCatching { context.packageManager.getPackageInfo(context.packageName, 0).versionName }
            .getOrDefault("")
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val onString = stringResource(id = R.string.app_notification_on)
    val offString = stringResource(id = R.string.app_notification_off)
    var isNotificationEnabledState by remember {
        mutableStateOf(getNotificationEnabled(context))
    }
    val withdrawalFailureMessage = stringResource(id = R.string.withdrawal_failure_message)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            isNotificationEnabledState = getNotificationEnabled(context)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Gray0),
        snackbarHost = {
            PicSnackbarHost(state = snackbarHostState)
        },
    ) { contentPadding ->
        MyPageDialog(
            dialogState = state.dialogState,
            onDismiss = {
                viewModel.dismissDialog()
            },
            onLogout = {
                viewModel.dismissDialog()
                viewModel.logout()
                navigateLoginAndFinish()
            },
            onWithdrawal = {
                viewModel.dismissDialog()
                viewModel.withdrawal(
                    onSuccess = { navigateLoginAndFinish() },
                )
            },
        )
        MyPageScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            userName = state.userName,
            notificationEnabledString = if (isNotificationEnabledState) onString else offString,
            versionName = versionName,
            onClickBack = onClickBack,
            onClickNotificationSetting = onClickNotificationSetting,
            showLogoutDialog = viewModel::showLogoutDialog,
            showWithdrawalDialog = viewModel::showWithdrawalDialog,
        )
    }
    if (state.errorMessage != null) {
        Log.d(TAG, "${state.errorMessage}")
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showPicSnackbar(
                type = PicSnackbarType.WARNING,
                message = withdrawalFailureMessage,
            )
        }
    }
}

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    userName: String,
    notificationEnabledString: String,
    versionName: String,
    onClickBack: () -> Unit,
    onClickNotificationSetting: () -> Unit,
    showLogoutDialog: () -> Unit,
    showWithdrawalDialog: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PicBackButtonTopBar(
            titleText = stringResource(id = R.string.my_page),
            backButtonClicked = onClickBack,
        )
        UserContainer(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 14.dp, horizontal = 16.dp),
            userName = userName,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(Gray20),
        )
        GroupTitle(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.notification_setting),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = onClickNotificationSetting)
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.app_notification_setting),
            subText = notificationEnabledString,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(2.dp)
                .background(Gray20),
        )
        GroupTitle(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.account_setting),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.current_version),
            subText = versionName,
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = showLogoutDialog)
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.logout),
        )
        GroupItemNormal(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = showWithdrawalDialog)
                .padding(vertical = 20.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.account_withdrawal),
        )
    }
}

fun getNotificationEnabled(context: Context): Boolean =
    ((context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.areNotificationsEnabled() == false).not()
