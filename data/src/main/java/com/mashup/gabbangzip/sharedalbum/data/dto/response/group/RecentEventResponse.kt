package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.data.common.toLocalDateTime
import com.mashup.gabbangzip.sharedalbum.domain.model.group.RecentEventDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecentEventResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "deadline")
    val deadline: String?,
) {
    fun toDomainModel(): RecentEventDomainModel {
        return RecentEventDomainModel(
            id = id,
            title = name,
            date = date?.toLocalDateTime(),
            deadline = deadline?.toLocalDateTime(),
        )
    }
}
