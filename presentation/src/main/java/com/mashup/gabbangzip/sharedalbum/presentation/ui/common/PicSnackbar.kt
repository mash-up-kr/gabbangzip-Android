package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.BlackAlpha50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType

@Composable
fun PicSnackbarHost(
    state: SnackbarHostState,
) {
    val configuration = LocalConfiguration.current
    val snackbarPosition = remember(configuration) {
        (configuration.screenHeightDp - 54).dp
    }

    SnackbarHost(
        modifier = Modifier.padding(bottom = snackbarPosition),
        hostState = state,
    ) { data ->
        with(data.visuals) {
            PicSnackbar(
                type = PicSnackbarType.find(actionLabel),
                message = message,
            )
        }
    }
}

@Composable
private fun PicSnackbar(
    type: PicSnackbarType,
    message: String,
) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = Color.Black.copy(alpha = 0.5f))
            .wrapContentSize()
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (type.iconResId != null) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = type.iconResId),
                contentDescription = "snackbar icon",
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .padding(vertical = 2.dp),
            text = message,
            color = Gray0,
            style = PicTypography.bodyMedium16,
        )
    }
}

suspend fun SnackbarHostState.showPicSnackbar(
    type: PicSnackbarType = PicSnackbarType.NORMAL,
    message: String,
) {
    showSnackbar(
        message = message,
        actionLabel = type.name,
        duration = SnackbarDuration.Short,
    )
}

@Preview
@Composable
private fun PicSnackbarPreview() {
    SharedAlbumTheme {
        PicSnackbar(
            type = PicSnackbarType.WARNING,
            message = "로그인에 실패했어요.",
        )
    }
}
