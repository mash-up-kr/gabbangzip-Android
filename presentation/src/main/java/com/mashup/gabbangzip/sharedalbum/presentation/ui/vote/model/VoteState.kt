package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.UserProfile
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList

data class VoteState(
    val photoList: ImmutableList<Photo>,
    val userProfile: UserProfile,
    val isLoading: Boolean,
) {
    companion object {
        fun getVoteTempState(): VoteState {
            return VoteState(
                photoList = ImmutableList(Photo.getTempList()),
                userProfile = UserProfile.getTempUserProfile(),
                isLoading = false,
            )
        }
    }
}
