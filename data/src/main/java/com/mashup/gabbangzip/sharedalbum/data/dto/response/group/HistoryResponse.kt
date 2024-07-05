package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.HistoryDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistoryResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "images")
    val images: List<CardBackImageResponse>,
)

fun HistoryResponse.toDomainModel(): HistoryDomainModel {
    return HistoryDomainModel(
        id = id,
        name = name,
        date = date,
        images = images.map { it.toDomainModel() }
    )
}
