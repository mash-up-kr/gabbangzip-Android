package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicKeywordButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyWord

@Composable
fun GroupCreationKeywordScreen(
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    val (selectedKeyword, setSelected) = remember { mutableStateOf(GroupKeyWord.SCHOOL) }

    Column {
        PicBackButtonTopBar(
            modifier = Modifier
                .background(Gray0.copy(alpha = 0.2f))
                .padding(top = 8.dp),
            titleText = stringResource(id = R.string.group_creation_button_name),
            backButtonClicked = onBackButtonClicked,
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
        ) {
            PicProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                level = 2,
                total = 4f,
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = stringResource(id = R.string.group_creation_keyword_title),
                style = PicTypography.headBold18,
                color = Gray80,
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(GroupKeyWord.entries) { groupKeyword ->
                    PicKeywordButton(
                        keyword = groupKeyword,
                        selected = selectedKeyword == groupKeyword,
                        onButtonClicked = { keyword -> setSelected(keyword) },
                    )
                }
            }
        }
        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
            text = stringResource(id = R.string.group_creation_button_next),
            isRippleClickable = true,
            enable = true,
            onButtonClicked = onNextButtonClicked,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupCreationKeywordScreenPreview() {
    SharedAlbumTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray0),
        ) {
            GroupCreationKeywordScreen(
                onBackButtonClicked = {},
                onNextButtonClicked = {},
            )
        }
    }
}
