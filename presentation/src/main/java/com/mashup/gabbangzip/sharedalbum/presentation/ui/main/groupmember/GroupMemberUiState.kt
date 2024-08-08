package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.Member
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class GroupMemberUiState(
    val keyWord: GroupKeyword = GroupKeyword.SCHOOL,
    val members: ImmutableList<Member> = ImmutableList(emptyList()),
    val invitationCode: String = ""
) {
    val isFull: Boolean = members.size == 6
}
