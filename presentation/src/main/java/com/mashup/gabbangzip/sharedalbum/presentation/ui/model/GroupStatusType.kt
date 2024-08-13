package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.ClickType

enum class GroupStatusType {
    NO_PAST_AND_CURRENT_EVENT,
    NO_CURRENT_EVENT,
    BEFORE_MY_UPLOAD,
    AFTER_MY_UPLOAD,
    BEFORE_MY_VOTE,
    AFTER_MY_VOTE,
    EVENT_COMPLETED,
    ;

    companion object {
        fun getType(status: String): GroupStatusType {
            return entries.associateBy { it.name }[status] ?: NO_PAST_AND_CURRENT_EVENT
        }

        fun getButtonRes(type: GroupStatusType): Triple<Int, Int, ClickType>? {
            return when (type) {
                AFTER_MY_VOTE, AFTER_MY_UPLOAD -> {
                    Triple(R.drawable.ic_group_notice, R.string.group_home_pic_fcm, ClickType.Fcm)
                }

                BEFORE_MY_UPLOAD -> {
                    Triple(
                        R.drawable.ic_btn_gallery,
                        R.string.group_home_pic_upload,
                        ClickType.Gallery,
                    )
                }

                BEFORE_MY_VOTE -> {
                    Triple(
                        R.drawable.ic_btn_vote,
                        R.string.group_home_select_pic,
                        ClickType.MyPick,
                    )
                }

                else -> null
            }
        }
    }
}
