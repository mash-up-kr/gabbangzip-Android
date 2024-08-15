package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VotePhotoDomainModel

data class VotePhoto(
    val id: Long,
    val imageUrl: String,
)

fun VotePhotoDomainModel.toUiModel(): VotePhoto =
    VotePhoto(id = id, imageUrl = imageUrl)

fun List<VotePhotoDomainModel>.toUiModel(): List<VotePhoto> = map { it.toUiModel() }
