package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarIcon
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.TopBarTitleAlign
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.Member
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyWord
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

@Composable
fun GroupMemberScreen(onClickBackButton: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            leftIcon = TopBarIcon(
                size = 26.dp,
                leftPadding = 16.dp,
                description = "뒤로가기",
                resId = R.drawable.ic_call_answer_low,
                iconClickListener = onClickBackButton,
            ),
            titleText = "그룹 멤버",
            titleAlign = TopBarTitleAlign.CENTER,
            topPadding = 10.dp,
            bottomPadding = 10.dp,
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .weight(1.0f),
            text = "그룹 멤버 화면입니다",
        )
    }
}

@Composable
private fun GroupMemberScreen(
    state: GroupMemberUiState,
    onClickBackButton: () -> Unit,
) {

}

@Composable
private fun GroupMemberList(
    modifier: Modifier = Modifier,
    type: GroupKeyWord,
    list: ImmutableList<Member>,
) {

}

@Composable
private fun GroupMemberItem(
    modifier: Modifier = Modifier,
    type: GroupKeyWord,
    member: Member,
) {

}

@Composable
private fun InvitationSection(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onButtonClick: () -> Unit,
) {

}
