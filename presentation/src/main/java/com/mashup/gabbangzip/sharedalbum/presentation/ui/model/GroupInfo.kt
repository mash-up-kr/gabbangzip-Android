package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class GroupInfo(
    val id: Int,
    val date: String,
    val groupName: String,
    val eventName: String,
    val thumbnailUrl: String,
    val thumbnailFrameUrl: String,
    val fourCutImageUrl: String,
    val keyword: GroupKeyword,
    val tags: ImmutableList<String>,
)
