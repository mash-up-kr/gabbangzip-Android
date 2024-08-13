package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import com.mashup.gabbangzip.sharedalbum.presentation.R

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

        fun getButtonRes(type: GroupStatusType): Pair<Int, Int>? {
            return when (type) {
                AFTER_MY_VOTE, AFTER_MY_UPLOAD -> {
                    Pair(R.drawable.ic_group_notice, R.string.group_home_pic_fcm)
                }

                BEFORE_MY_UPLOAD -> {
                    Pair(R.drawable.ic_btn_gallery, R.string.group_home_pic_upload)
                }

                BEFORE_MY_VOTE -> {
                    Pair(R.drawable.ic_btn_vote, R.string.group_home_select_pic)
                }

                else -> null
            }
        }
    }
}
