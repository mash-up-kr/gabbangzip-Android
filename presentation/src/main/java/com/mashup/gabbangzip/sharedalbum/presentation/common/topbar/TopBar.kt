package com.mashup.gabbangzip.sharedalbum.presentation.common.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type.TopBarMain
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type.TopBarProgress
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.type.TopBarTitle

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    topBarState: TopBarState,
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

@Preview
@Composable
fun TopBarPreview(
    @PreviewParameter(TopBarProvider::class) state: TopBarState,
) {
    TopBar(
        modifier = Modifier,
        topBarState = state,
    )
}
