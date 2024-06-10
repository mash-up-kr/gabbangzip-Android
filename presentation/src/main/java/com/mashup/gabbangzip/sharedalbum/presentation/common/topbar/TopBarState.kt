package com.mashup.gabbangzip.sharedalbum.presentation.common.topbar

sealed class TopBarState {
    data object None : TopBarState()

    data class Title(
        val titleText: String,
    ) : TopBarState()

    data class Main(
        val titleText: String,
        val iconRes: Int,
    ) : TopBarState()

    data class Progress(
        val titleText: String,
        val max: Int,
        val progress: Int,
    ) : TopBarState()
}
