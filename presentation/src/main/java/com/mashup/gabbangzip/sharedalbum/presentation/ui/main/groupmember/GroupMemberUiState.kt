package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.Member
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class GroupMemberUiState(
    val keyWord: GroupKeyword = GroupKeyword.SCHOOL,
    val members: ImmutableList<Member> = ImmutableList(emptyList()),
    val invitationCode: String = "",
    val isLoading: Boolean = false,
) {
    val isFull: Boolean = members.size == 6
}

sealed interface GroupMemberEvent {
    data object SuccessWithdrawGroup : GroupMemberEvent
    data class FailureWithdrawGroup(@StringRes val message: Int) : GroupMemberEvent
}
