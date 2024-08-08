package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.group.MemberListDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupMemberResponse(
    @Json(name = "members")
    val members: List<MemberResponse>,
    @Json(name = "invitation_code")
    val invitationCode: String,
) {
    fun toDomainModel(): MemberListDomainModel {
        return MemberListDomainModel(
            members = members.map(MemberResponse::toDomainModel),
            invitationCode = invitationCode,
        )
    }
}

@JsonClass(generateAdapter = true)
data class MemberResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "nickname")
    val nickname: String,
) {
    fun toDomainModel(): MemberDomainModel {
        return MemberDomainModel(
            id = id,
            nickname = nickname,
        )
    }
}
