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
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicTopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicTopBarIcon
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
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        PicTopBar(
            rightIcon1 = PicTopBarIcon.PLUS,
            rightIcon2 = PicTopBarIcon.USER,
            rightIcon1Clicked = { /* TODO: plus버튼. 그룹 만들기 */ },
            rightIcon2Clicked = { /*TODO: 마이페이지로 가기*/ },
        )

        LazyColumn {
            itemsIndexed(state.groupList) { index, groupInfo ->
                GroupContainer(
                    modifier = if (index == 0) {
                        Modifier.padding(top = 16.dp)
                    } else if (index == state.groupList.size - 1) {
                        Modifier.padding(bottom = 16.dp)
                    } else {
                        Modifier
                    },
                    groupInfo = groupInfo,
                    onGroupDetailClick = onClickGroupDetail,
                )

                if (state.groupList.size != index + 1) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            keyword = groupInfo.keyword,
            statusDesc = groupInfo.statusDescription,
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
    statusDesc: String,
) {
    Row(modifier = modifier) {
        PicTag(
            modifier = Modifier.padding(end = 6.dp),
            text = keyword.name,
            iconRes = keyword.symbolResId,
        )
        PicTag(
            text = statusDesc,
        )
    }
}
