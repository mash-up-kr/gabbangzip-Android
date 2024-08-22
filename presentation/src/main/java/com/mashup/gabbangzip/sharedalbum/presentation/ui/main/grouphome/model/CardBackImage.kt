package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import com.mashup.gabbangzip.sharedalbum.domain.model.group.CardBackImageDomainModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.PicPhotoFrame
import java.io.Serializable

data class CardBackImage(
    val frameType: PicPhotoFrame,
    val imageUrl: String,
) : Serializable

fun CardBackImageDomainModel.toUiModel(): CardBackImage {
    return CardBackImage(
        frameType = PicPhotoFrame.getTypeByName(frameType),
        imageUrl = imageUrl,
    )
}

fun List<CardBackImageDomainModel>.toUiModel(): List<CardBackImage> {
    return if (size >= 4) {
        take(4).toUiModel()
    } else {
        emptyList()
    }
}
