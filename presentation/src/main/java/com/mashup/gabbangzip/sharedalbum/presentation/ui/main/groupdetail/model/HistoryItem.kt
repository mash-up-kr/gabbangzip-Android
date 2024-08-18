package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.HistoryDomainModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.CardBackImage
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import com.mashup.gabbangzip.sharedalbum.presentation.utils.LocalDateUtil
import java.io.Serializable

data class HistoryItem(
    val id: Long = 0,
    val title: String,
    val date: String,
    val images: ImmutableList<CardBackImage> = ImmutableList(emptyList()),
) : Serializable

fun HistoryDomainModel.toUiModel(): HistoryItem {
    return HistoryItem(
        id = id,
        title = name,
        date = LocalDateUtil.format(date),
        images = ImmutableList(images.toUiModel()),
    )
}
