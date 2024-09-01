package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.keyword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray0Alpha80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicBackButtonTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicKeywordButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicProgressBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.common.GroupCreationScaffold
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

@Composable
fun GroupCreationKeywordScreen(
    initialKeyword: GroupKeyword,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: (keyword: GroupKeyword) -> Unit,
) {
    val (selectedKeyword, setSelected) = remember { mutableStateOf(initialKeyword) }
    GroupCreationKeywordScreen(
        selectedKeyword,
        setSelected,
        onBackButtonClicked,
        onNextButtonClicked,
    )
}

@Composable
private fun GroupCreationKeywordScreen(
    selectedKeyword: GroupKeyword,
    setSelected: (GroupKeyword) -> Unit,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: (keyword: GroupKeyword) -> Unit,
) {
    GroupCreationScaffold(
        contentPadding = PaddingValues(horizontal = 16.dp),
        topBar = {
            PicBackButtonTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Gray0Alpha80)
                    .padding(top = 30.dp, bottom = 14.dp, start = 16.dp, end = 16.dp),
                titleText = stringResource(id = R.string.group_creation_button_name),
                backButtonClicked = onBackButtonClicked,
            )
            PicProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                level = 2,
                total = 4f,
            )
        },
        bottomFloatingButton = {
            PicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 22.dp, bottom = 16.dp),
                text = stringResource(id = R.string.next),
                isRippleClickable = true,
                enable = true,
                onButtonClicked = { onNextButtonClicked(selectedKeyword) },
            )
        },
        boxContent = {
            GroupCreationKeywordContent(
                selectedKeyword = selectedKeyword,
                setSelected = setSelected,
            )
        },
    )
}

@Composable
private fun GroupCreationKeywordContent(
    selectedKeyword: GroupKeyword,
    setSelected: (GroupKeyword) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item(span = { GridItemSpan(3) }) {
            Text(
                modifier = Modifier.padding(top = 32.dp, bottom = 24.dp),
                text = stringResource(id = R.string.group_creation_keyword_title),
                style = PicTypography.headBold18,
                color = Gray80,
            )
        }
        items(GroupKeyword.entries) { keyword ->
            PicKeywordButton(
                keyword = keyword,
                selected = selectedKeyword == keyword,
                onButtonClicked = { setSelected(it) },
            )
        }
        item(span = { GridItemSpan(3) }) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupCreationKeywordScreenPreview() {
    SharedAlbumTheme {
        GroupCreationKeywordScreen(
            selectedKeyword = GroupKeyword.SCHOOL,
            setSelected = {},
            onBackButtonClicked = {},
            onNextButtonClicked = {},
        )
    }
}
