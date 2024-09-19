package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Cultured
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.utils.LocalDateUtil
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicDatePickerDialog(
    modifier: Modifier = Modifier,
    date: LocalDateTime = LocalDateTime.now(),
    onClickedDismiss: () -> Unit = {},
    onClickedConfirm: () -> Unit = {},
) {
    val datePickerState = rememberDatePickerState(
        yearRange = 2020..2030,
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = LocalDateUtil.getTimeMillis(date),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean = true
        },
    )

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onClickedDismiss,
        confirmButton = {},
        colors = DatePickerDefaults.colors(containerColor = Gray0),
        shape = RoundedCornerShape(20.dp),
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        DatePicker(
            modifier = Modifier.padding(top = 16.dp),
            state = datePickerState,
            title = null,
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                containerColor = Gray0,
                headlineContentColor = Gray80,
                selectedDayContainerColor = Gray80,
                selectedDayContentColor = Gray0,
                currentYearContentColor = Gray80,
                selectedYearContainerColor = Gray80,
                selectedYearContentColor = Gray0,
                todayDateBorderColor = Gray80,
                todayContentColor = Gray80,
                dividerColor = Color.Transparent,
            ),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            PicDialogButton(
                modifier = Modifier.weight(0.5f),
                text = stringResource(R.string.event_creation_dialog_dismiss),
                isRippleClickable = true,
                backgroundColor = Cultured,
                contentColor = Gray80,
                onButtonClicked = onClickedDismiss,
            )
            PicDialogButton(
                modifier = Modifier.weight(0.5f),
                text = stringResource(R.string.date_picker_dialog_confirm),
                isRippleClickable = true,
                onButtonClicked = onClickedConfirm,
            )
        }
    }
}
