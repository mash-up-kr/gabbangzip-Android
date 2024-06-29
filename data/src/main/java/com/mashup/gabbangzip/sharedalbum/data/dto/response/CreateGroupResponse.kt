package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.mashup.gabbangzip.sharedalbum.domain.model.GroupKeyWord
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateGroupResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "group_name")
    val groupName: String,
    @Json(name = "keyword")
    val keyword: GroupKeyWord?,
    @Json(name = "group_image_url")
    val groupImageUrl: String,
    @Json(name = "group_invitation_url")
    val groupInvitationUrl: String,
)
