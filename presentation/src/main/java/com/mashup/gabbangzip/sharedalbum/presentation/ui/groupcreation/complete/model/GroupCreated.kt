package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

data class GroupCreated(
    val name: String,
    val keyword: GroupKeyword,
    val imageUrl: String,
    val invitationUrl: String,
)
