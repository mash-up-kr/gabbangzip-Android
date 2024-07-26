package com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation

import android.net.Uri
import com.mashup.gabbangzip.sharedalbum.presentation.ui.groupcreation.complete.model.GroupCreated
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

data class GroupCreationState(
    val name: String = "",
    val keyword: GroupKeyword = GroupKeyword.SCHOOL,
    val thumbnail: Uri? = null,
    val groupCreated: GroupCreated? = null,
) {
    val isGroupCreated get() = groupCreated != null
}
