package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType

class GroupEventActionButtonState(
    @DrawableRes val iconResId: Int,
    @StringRes val textResId: Int? = null,
    @StringRes val informationTextResId: Int? = null,
)

fun GroupStatusType.getActionButtonState(): GroupEventActionButtonState? {
    return when (this) {
        GroupStatusType.BEFORE_MY_UPLOAD -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_gallery_white,
                textResId = R.string.button_text_state_before_my_upload,
                informationTextResId = R.string.information_text_state_before_my_upload,
            )
        }

        GroupStatusType.AFTER_MY_UPLOAD -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_group_notice,
                textResId = R.string.button_text_state_after_my_upload,
                informationTextResId = R.string.information_text_state_after_my_upload,
            )
        }

        GroupStatusType.BEFORE_MY_VOTE -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_vote,
                textResId = R.string.button_text_state_before_my_vote,
                informationTextResId = R.string.information_text_state_before_my_vote,
            )
        }

        GroupStatusType.AFTER_MY_VOTE -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_group_notice,
                textResId = R.string.button_text_state_after_my_vote,
                informationTextResId = R.string.information_text_state_after_my_vote,
            )
        }

        GroupStatusType.EVENT_COMPLETED -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_share,
            )
        }

        else -> null
    }
}
