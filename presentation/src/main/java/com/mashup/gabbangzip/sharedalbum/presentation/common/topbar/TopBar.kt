package com.mashup.gabbangzip.sharedalbum.presentation.common.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
