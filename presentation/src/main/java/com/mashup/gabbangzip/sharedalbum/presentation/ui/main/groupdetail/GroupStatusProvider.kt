package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType

class GroupStatusProvider : PreviewParameterProvider<GroupStatusType> {
    override val values: Sequence<GroupStatusType>
        get() = sequenceOf(
            GroupStatusType.NO_PAST_AND_CURRENT_EVENT,
            GroupStatusType.NO_CURRENT_EVENT,
            GroupStatusType.BEFORE_MY_UPLOAD,
            GroupStatusType.AFTER_MY_UPLOAD,
            GroupStatusType.BEFORE_MY_VOTE,
            GroupStatusType.AFTER_MY_VOTE,
            GroupStatusType.EVENT_COMPLETED,
        )
}
