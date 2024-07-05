package com.mashup.gabbangzip.sharedalbum.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray50
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray60
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun PicKeywordButton(
    modifier: Modifier = Modifier,
    keyword: GroupKeyword,
    selected: Boolean,
    textStyle: TextStyle = PicTypography.bodyMedium16,
    onButtonClicked: (GroupKeyword) -> Unit,
) {
    Box(
        modifier = modifier
            .noRippleClickable { onButtonClicked(keyword) }
            .background(
                color = if (selected) keyword.backgroundColor else Gray40,
                shape = RoundedCornerShape(20.dp),
            )
            .padding(horizontal = 22.dp, vertical = 18.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(id = keyword.tagNameResId),
                style = textStyle,
                color = if (selected) Gray80 else Gray60,
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = keyword.symbolResId),
                contentDescription = stringResource(R.string.pic_keyword_button, keyword.name),
                colorFilter = ColorFilter.tint(if (selected) keyword.symbolColor else Gray50),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PicKeywordButtonPreview() {
    SharedAlbumTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            PicKeywordButton(
                keyword = GroupKeyword.EXERCISE,
                selected = true,
                onButtonClicked = {},
            )
            PicKeywordButton(
                keyword = GroupKeyword.EXERCISE,
                selected = false,
                onButtonClicked = {},
            )
        }
    }
}
