package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray100
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

data class FilterTag(
    val name: String,
    @DrawableRes val symbolResId: Int?,
    @DrawableRes val symbolColor: Color,
    @StringRes val tagNameResId: Int,
    val isSelected: Boolean,
) {
    companion object {
        fun getTotalFilter(isSelected: Boolean = false) = FilterTag(
            name = GroupKeyword.TOTAL,
            symbolResId = null,
            symbolColor = Gray100,
            tagNameResId = R.string.tag_total,
            isSelected = isSelected,
        )
    }
}

fun GroupKeyword.toFilterTag(isSelected: Boolean = false) = FilterTag(
    name = name,
    symbolResId = symbolResId,
    symbolColor = symbolColor,
    tagNameResId = tagNameResId,
    isSelected = isSelected,
)

fun List<GroupKeyword>.toFilterTagList() = map { it.toFilterTag() }
