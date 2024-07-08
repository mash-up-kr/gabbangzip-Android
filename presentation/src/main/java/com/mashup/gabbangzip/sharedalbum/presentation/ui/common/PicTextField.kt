package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme

@Composable
fun PicTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    maxLength: Int = 0,
) {
    BasicTextField(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Gray20)
            .border(
                width = 1.dp,
                color = Gray50,
                shape = RoundedCornerShape(10.dp),
            )
            .padding(horizontal = 20.dp, vertical = 18.dp),
        value = value,
        onValueChange = {
            if (maxLength == 0 || it.length <= maxLength) {
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
        ),
        textStyle = PicTypography.bodyMedium16.copy(color = Gray80),
        maxLines = 1,
        cursorBrush = SolidColor(Gray80),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = hint,
                    style = PicTypography.bodyMedium16,
                    color = Gray60,
                )
            }
            innerTextField()
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PicTextFieldPreview() {
    SharedAlbumTheme {
        Column(modifier = Modifier.background(Gray80)) {
            var text by remember { mutableStateOf("") }
            PicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = text,
                onValueChange = { text = it },
                hint = "최대 10자 까지 입력 가능해요.",
                maxLength = 10,
            )
        }
    }
}
