package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.CardBackImageDomainModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame

data class CardBackImage(
    val frameType: PicPhotoFrame,
    val imageUrl: String,
)

fun CardBackImageDomainModel.toUiModel(): CardBackImage {
    return CardBackImage(
        frameType = PicPhotoFrame.getType(frameType),
        imageUrl = imageUrl,
    )
}

fun List<CardBackImageDomainModel>.toUiModel(): List<CardBackImage> {
    return map { it.toUiModel() }
}
