package com.mashup.gabbangzip.sharedalbum.presentation.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            text = "스플래시 화면입니다",
        )
    }
}
