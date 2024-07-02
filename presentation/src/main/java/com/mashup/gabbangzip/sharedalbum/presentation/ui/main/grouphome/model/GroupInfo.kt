package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyWord

data class GroupInfo(
    val id: Long,
    val cardBackImages: List<CardBackImage>,
    val cardFrontImageUrl: String,
    val keyword: GroupKeyWord,
    val name: String,
    val recentEventDate: String,
    val status: GroupStatusType,
    val statusDescription: String,
)

fun GroupDomainModel.toUiModel(): GroupInfo {
    return GroupInfo(
        id = id,
        cardBackImages = cardBackImages.toUiModel(),
        cardFrontImageUrl = cardFrontImageUrl,
        keyword = GroupKeyWord.getKeyword(keyword),
        name = name,
        recentEventDate = recentEventDate,
        status = GroupStatusType.getType(status),
        statusDescription = statusDescription,
    )
}

fun List<GroupDomainModel>.toUiModel(): List<GroupInfo> {
    return map { it.toUiModel() }
}
