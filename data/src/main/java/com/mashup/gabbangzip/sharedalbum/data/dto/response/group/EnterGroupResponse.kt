package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnterGroupResponse(
    @Json(name = "group_id")
    val groupId: Long,
)
