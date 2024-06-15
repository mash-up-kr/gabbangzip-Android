package com.mashup.gabbangzip.sharedalbum.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.common.Route
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.TopBar
import com.mashup.gabbangzip.sharedalbum.presentation.main.navigation.MainNavHost
import com.mashup.gabbangzip.sharedalbum.presentation.main.navigation.MainNavigator

@Composable
fun MainScreen(
    navigator: MainNavigator,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val topBarState by viewModel.topBarStateFlow.collectAsState()

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                TopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    topBarState = topBarState,
                )
                MainNavHost(
                    modifier = Modifier.weight(weight = 1f),
                    navigator = navigator,
                    startDestination = Route.initRoute,
                    viewModel = viewModel,
                )
            }
        },
    )
}
