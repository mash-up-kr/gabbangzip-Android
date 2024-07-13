package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType

data class GroupEvent(
    val title: String,
    val date: String,
    val status: GroupStatusType,
    val deadline: String,
)
