package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "keyword")
    val keyword: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "status_description")
    val statusDescription: String,
    @Json(name = "recent_event")
    val recentEvent: RecentEventResponse,
    @Json(name = "card_back_images")
    val cardBackImages: List<CardBackImageResponse>?,
    @Json(name = "card_front_image_url")
    val cardFrontImageUrl: String,
)

fun GroupResponse.toDomainModel(): GroupDomainModel {
    return GroupDomainModel(
        id = id,
        cardBackImages = cardBackImages?.map { it.toDomainModel() } ?: emptyList(),
        cardFrontImageUrl = cardFrontImageUrl,
        keyword = keyword,
        name = name,
        recentEvent = recentEvent.toDomainModel(),
        status = status,
        statusDescription = statusDescription,
    )
}

fun List<GroupResponse>.toDomainModel(): List<GroupDomainModel> {
    return map { it.toDomainModel() }
}
