package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword

data class FilterTagUiModel(
    val name: String,
    @DrawableRes val symbolResId: Int,
    @StringRes val tagNameResId: Int,
    val isSelected: Boolean,
)

fun GroupKeyword.toFilterTagUiModel() = FilterTagUiModel(
    name = name,
    symbolResId = symbolResId,
    tagNameResId = tagNameResId,
    isSelected = false,
)
