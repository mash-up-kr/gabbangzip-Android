package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.GroupEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupStatusType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame

data class GroupInfo(
    val id: Long,
    val cardBackImages: List<CardBackImage>,
    val cardFrontImageUrl: String,
    val frontImageFrame: PicPhotoFrame,
    val keyword: GroupKeyword,
    val name: String,
    val recentEvent: GroupEvent,
    val status: GroupStatusType,
    val statusDescription: String,
    val history: List<HistoryItem> = emptyList(),
)

fun GroupDomainModel.toUiModel(): GroupInfo {
    return GroupInfo(
        id = id,
        cardBackImages = cardBackImages.toUiModel(),
        cardFrontImageUrl = cardFrontImageUrl,
        keyword = GroupKeyword.getKeyword(keyword),
        frontImageFrame = PicPhotoFrame.getTypeByKeyword(keyword),
        name = name,
        recentEvent = recentEvent.toUiModel(),
        status = GroupStatusType.getType(status),
        statusDescription = statusDescription,
        history = history.map { it.toUiModel() },
    )
}

fun List<GroupDomainModel>.toUiModel(): List<GroupInfo> {
    return map { it.toUiModel() }
}
