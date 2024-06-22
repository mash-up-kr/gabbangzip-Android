package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

data class UserProfile(
    val imageUrl: String,
    val name: String,
) {
    companion object {
        fun getTempUserProfile(): UserProfile {
            return UserProfile(
                imageUrl = "https://picsum.photos/id/1/200/300",
                name = "서정우",
            )
        }
    }
}
