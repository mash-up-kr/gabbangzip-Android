package com.mashup.gabbangzip.sharedalbum.data.dto.request

import com.mashup.gabbangzip.sharedalbum.domain.model.GroupKeyword
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateGroupRequest(
    @Json(name = "group_name")
    val groupName: String,
    @Json(name = "keyword")
    val keyword: GroupKeyword,
    @Json(name = "group_image_url")
    val groupImageUrl: String,
)
