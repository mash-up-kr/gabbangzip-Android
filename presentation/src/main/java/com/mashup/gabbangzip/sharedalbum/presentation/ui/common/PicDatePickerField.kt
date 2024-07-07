package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.utils.StableImage

@Composable
fun PicDatePickerField(
    modifier: Modifier = Modifier,
    date: String = "",
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Gray40)
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        StableImage(
            drawableResId = R.drawable.ic_calender,
            contentDescription = stringResource(id = R.string.pic_calendar),
        )
        Text(
            text = date.ifEmpty { stringResource(id = R.string.pic_date_default) },
            style = PicTypography.bodyMedium16,
            color = if (date.isEmpty()) Gray60 else Gray80,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PicDatePickerFieldPreview() {
    PicDatePickerField(
        modifier = Modifier.fillMaxWidth(),
        date = "24/10/26",
    )
}
