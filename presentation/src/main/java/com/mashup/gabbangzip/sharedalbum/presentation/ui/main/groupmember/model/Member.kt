package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember.model

data class Member(
    val id: Long,
    val name: String,
    val isLeader: Boolean = false,
)
