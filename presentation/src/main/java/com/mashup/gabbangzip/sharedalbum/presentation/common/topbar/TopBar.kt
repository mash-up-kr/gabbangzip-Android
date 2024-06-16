package com.mashup.gabbangzip.sharedalbum.presentation.common.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.navigation.NavHostController
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.common.LoginRoute
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type.TopBarMain
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type.TopBarProgress
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type.TopBarTitle
import com.mashup.gabbangzip.sharedalbum.presentation.main.MainViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.main.navigation.MainNavigator

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    topBarState: TopBarState,
    navigator: MainNavigator,
    viewModel: MainViewModel,
) {
    when (topBarState) {
        is TopBarState.None -> Unit

        is TopBarState.Title -> TopBarTitle(
            modifier = modifier,
            titleText = topBarState.titleText,
        )

        is TopBarState.Main -> TopBarMain(
            modifier = modifier,
            titleText = topBarState.titleText,
            iconRes = topBarState.iconRes,
            onClickTitle = {
                viewModel.updateTopBar(LoginRoute)
                navigator.navigateLogin()
            }
        )

        is TopBarState.Progress -> TopBarProgress(
            modifier = modifier,
            titleText = topBarState.titleText,
            max = topBarState.max,
            progress = topBarState.progress,
        )
    }
}

class TopBarProvider : PreviewParameterProvider<TopBarState> {
    override val values: Sequence<TopBarState> = sequenceOf(
        TopBarState.Title(titleText = "제목만 있는 탑바"),
        TopBarState.Main(titleText = "제목만 있는 탑바", iconRes = R.drawable.ic_launcher_foreground),
        TopBarState.Progress(titleText = "제목만 있는 탑바", max = 3, progress = 1),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar(@PreviewParameter(TopBarProvider::class) topBarState: TopBarState) {
    val context = LocalContext.current
    TopBar(
        topBarState = topBarState,
        navigator = MainNavigator(NavHostController(context)),
        viewModel = MainViewModel()
    )
}
