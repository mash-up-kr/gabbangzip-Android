package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.KeywordDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KeywordResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
)

fun KeywordResponse.toDomainModel(): KeywordDomainModel {
    return KeywordDomainModel(
        id = id,
        name = name,
    )
}
