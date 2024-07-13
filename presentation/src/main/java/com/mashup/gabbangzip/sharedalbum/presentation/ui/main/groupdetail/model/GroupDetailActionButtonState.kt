package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import androidx.annotation.DrawableRes
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType

class GroupEventActionButtonState(
    @DrawableRes val iconResId: Int,
    val text: String = "",
    val informationText: String = "",
)

fun GroupStatusType.getActionButtonState(): GroupEventActionButtonState? {
    return when (this) {
        GroupStatusType.BEFORE_MY_UPLOAD -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_gallery_white,
                text = "내 PIC 올리기",
                informationText = "까지 내 PIC 등록을 완료해 주세요.",
            )
        }

        GroupStatusType.AFTER_MY_UPLOAD -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_group_notice,
                text = "쿡 찌르기",
                informationText = "아직 사진 추가를 하지 않은 친구가 있어요!",
            )
        }

        GroupStatusType.BEFORE_MY_VOTE -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_vote,
                text = "투표하기",
                informationText = "내 PIC을 고르고 네컷사진을 완성해 보세요.",
            )
        }

        GroupStatusType.AFTER_MY_VOTE -> {
            GroupEventActionButtonState(
                iconResId = R.drawable.ic_group_notice,
                text = "쿡 찌르기",
                informationText = "아직 PIC을 고르지 않은 친구가 있어요!",
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
