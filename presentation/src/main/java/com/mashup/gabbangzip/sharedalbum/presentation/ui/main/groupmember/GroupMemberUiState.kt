package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.Member
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class GroupMemberUiState(
    val keyWord: GroupKeyword = GroupKeyword.SCHOOL,
    val members: ImmutableList<Member> = ImmutableList(emptyList()),
    val invitationCode: String = "",
    @StringRes val errorMessage: Int? = null,
    val isLoading: Boolean = false,
) {
    val isFull: Boolean = members.size == 6
}
