package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupDataResponse(
    @Json(name = "groups")
    val groups: List<GroupResponse>,
)

fun GroupDataResponse.toDomainModel(): List<GroupDomainModel> {
    return groups.map { it.toDomainModel() }
}
