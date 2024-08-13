package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.CardBackImage
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class HistoryItem(
    val id: Long = 0,
    val title: String,
    val date: String,
    val images: ImmutableList<CardBackImage> = ImmutableList(emptyList()),
)
