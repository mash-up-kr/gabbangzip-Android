package com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model

import androidx.annotation.DrawableRes
import com.mashup.gabbangzip.sharedalbum.presentation.R

enum class PicSnackbarType(
    @DrawableRes val iconResId: Int? = null,
) {
    NORMAL,
    CHECK(iconResId = R.drawable.ic_check),
    WARNING(iconResId = R.drawable.ic_warning),
    ;

    companion object {
        fun find(key: String?): PicSnackbarType {
            return entries.associateBy(PicSnackbarType::name)[key] ?: NORMAL
        }
    }
}
