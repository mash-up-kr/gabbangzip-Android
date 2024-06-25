package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.BlackAlpha50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme

enum class PicSnackbarType(
    @DrawableRes val iconResId: Int? = null,
) {
    NORMAL,
    CHECK(iconResId = R.drawable.ic_check),
    WARNING(iconResId = R.drawable.ic_warning),
    ;

    companion object {
        fun find(key: String?): PicSnackbarType {
            return entries.associateBy(PicSnackbarType::name)[key] ?: NORMAL
        }
    }
}

@Composable
fun PicSnackbarHost(
    state: SnackbarHostState,
) {
    SnackbarHost(
        hostState = state,
    ) { data ->
        PicSnackbar(
            type = PicSnackbarType.find(data.visuals.actionLabel),
            message = data.visuals.message,
        )
    }
}

@Composable
fun PicSnackbar(
    type: PicSnackbarType = PicSnackbarType.NORMAL,
    message: String,
) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = BlackAlpha50)
            .wrapContentSize()
            .padding(
                horizontal = 24.dp,
                vertical = 20.dp,
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
            text = message,
            color = Gray0,
            style = PicTypography.bodyMedium16,
        )
    }
}

@Preview
@Composable
fun PickSnackbarPreView() {
    SharedAlbumTheme {
        PicSnackbar(
            type = PicSnackbarType.WARNING,
            message = "로그인에 실패했어요.",
        )
    }
}
