package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray20
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicPhotoCard
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTag
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupHomeUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.noRippleClickable

@Composable
fun GroupHomeScreen(
    state: GroupHomeUiState,
    onClickGroupDetail: (id: Long) -> Unit,
    onClickEventMake: () -> Unit,
    onClickMyPage: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(state.groupList) {
            GroupContainer(
                modifier = Modifier,
                groupInfo = it,
                onGroupDetailClick = onClickGroupDetail,
            )

            if (state.groupList.last() != it) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 24.dp)
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(color = Gray20),
                )
            }
        }
    }
}

@Composable
private fun GroupContainer(
    modifier: Modifier,
    groupInfo: GroupInfo,
    onGroupDetailClick: (id: Long) -> Unit,
) {
    Column(
        modifier = modifier.noRippleClickable { onGroupDetailClick(groupInfo.id) },
    ) {
        GroupTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            groupName = groupInfo.name,
        )
        GroupTag(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            keyword = groupInfo.keyword,
            statusDsc = groupInfo.statusDescription,
        )
        PicPhotoCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 16.dp,
                    start = 46.dp,
                    end = 46.dp,
                ),
            groupInfo = groupInfo,
        )
    }
}

@Composable
private fun GroupTitle(modifier: Modifier, groupName: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Text(
            text = groupName,
            color = Gray80,
            style = PicTypography.headBold24,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = stringResource(R.string.detail_page_move),
        )
    }
}

@Composable
private fun GroupTag(
    modifier: Modifier,
    keyword: GroupKeyword,
    statusDsc: String,
) {
    Row(modifier = modifier) {
        PicTag(
            modifier = modifier.padding(end = 6.dp),
            text = keyword.name,
            iconRes = keyword.symbolResId,
        )
        PicTag(
            text = statusDsc,
        )
    }
}
