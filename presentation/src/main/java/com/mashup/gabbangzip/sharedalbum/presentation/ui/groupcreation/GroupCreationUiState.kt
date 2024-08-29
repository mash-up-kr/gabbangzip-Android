package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

import android.net.Uri
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.model.GroupCreationResult
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

data class GroupCreationUiState(
    val name: String = "",
    val keyword: GroupKeyword = GroupKeyword.SCHOOL,
    val thumbnail: Uri? = null,
    val groupCreationResult: GroupCreationResult? = null,
    val isLoading: Boolean = false,
) {
    val isGroupCreated get() = groupCreationResult != null
}
