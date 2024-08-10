package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model.Member
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

class GroupMemberProvider : PreviewParameterProvider<GroupMemberUiState> {
    override val values: Sequence<GroupMemberUiState>
        get() = sequenceOf(
            GroupMemberUiState(
                keyWord = GroupKeyword.EXERCISE,
                members = ImmutableList(
                    listOf(
                        Member(id = 0, name = "연규"),
                        Member(id = 1, name = "윤서"),
                        Member(id = 1, name = "윤서"),
                        Member(id = 1, name = "윤서"),
                        Member(id = 1, name = "윤서"),
                        Member(id = 1, name = "윤서"),
                    ),
                ),
            ),
            GroupMemberUiState(
                keyWord = GroupKeyword.SCHOOL,
                members = ImmutableList(
                    listOf(
                        Member(id = 0, name = "연규"),
                    ),
                ),
            ),
        )
}
