package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Malibu

@Composable
fun PicProgressBar(
    modifier: Modifier = Modifier,
    level: Int,
) {
    LinearProgressIndicator(
        modifier = modifier
            .height(4.dp)
            .clip(RoundedCornerShape(999.dp)),
        color = Gray80,
        trackColor = Gray20,
        strokeCap = StrokeCap.Round,
        progress = { (level / 4f) },
    )
}

@Preview(showBackground = true)
@Composable
fun PicProgressBarPreview() {
    PicProgressBar(
        modifier = Modifier.fillMaxWidth(),
        level = 1
    )
}
