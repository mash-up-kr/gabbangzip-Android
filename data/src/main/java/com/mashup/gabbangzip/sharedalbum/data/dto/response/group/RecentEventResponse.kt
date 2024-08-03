package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.RecentEventDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class RecentEventResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "date")
    val date: LocalDateTime,
    @Json(name = "deadline")
    val deadline: LocalDateTime?,
) {
    fun toDomainModel(): RecentEventDomainModel {
        return RecentEventDomainModel(
            title = name,
            date = date,
            deadline = deadline,
        )
    }
}
