package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.CardBackImageDomainModel

data class CardBackImage(
    val frameType: String,
    val imageUrl: String,
)

fun CardBackImageDomainModel.toUiModel(): CardBackImage {
    return CardBackImage(
        frameType = frameType,
        imageUrl = imageUrl,
    )
}

fun List<CardBackImageDomainModel>.toUiModel(): List<CardBackImage> {
    return map { it.toUiModel() }
}
