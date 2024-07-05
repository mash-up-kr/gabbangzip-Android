package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

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
    }
}
