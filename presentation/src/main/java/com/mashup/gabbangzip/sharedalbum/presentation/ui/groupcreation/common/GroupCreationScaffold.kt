package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GroupCreationScaffold(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment.Horizontal = Alignment.Start,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit,
    topBar: @Composable () -> Unit = {},
    bottomFloatingButton: @Composable BoxScope.() -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier) {
        topBar()
        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .verticalScroll(scrollState),
                horizontalAlignment = contentAlignment,
                content = content,
            )
            Box(
                modifier = Modifier.align(Alignment.BottomCenter),
                content = bottomFloatingButton,
            )
        }
    }
}
