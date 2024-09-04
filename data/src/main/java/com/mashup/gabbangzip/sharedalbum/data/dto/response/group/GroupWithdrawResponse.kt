package com.mashup.gabbangzip.sharedalbum.data.dto.response.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.WithdrawalGroupDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupWithdrawResponse(
    @Json(name = "group_id")
    val groupId: Long,
)

fun GroupWithdrawResponse.toDomainModel(): WithdrawalGroupDomainModel {
    return WithdrawalGroupDomainModel(
        groupId = groupId,
    )
}
