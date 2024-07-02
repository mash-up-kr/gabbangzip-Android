package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.Member
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyWord
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class GroupMemberUiState(
    val keyWord: GroupKeyWord = GroupKeyWord.SCHOOL,
    val members: ImmutableList<Member> = ImmutableList(emptyList()),
) {
    val isFull: Boolean = members.size == 6
}
