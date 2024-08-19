package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import com.mashup.gabbangzip.sharedalbum.domain.model.UserInfoDomainModel

data class UserInfo(
    val name: String = "",
)

fun UserInfoDomainModel.toUiModel() = UserInfo(name = name)
