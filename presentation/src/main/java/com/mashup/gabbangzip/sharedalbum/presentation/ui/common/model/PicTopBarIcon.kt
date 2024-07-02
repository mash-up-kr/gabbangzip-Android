package com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mashup.gabbangzip.sharedalbum.presentation.R

enum class PicTopBarIcon(@DrawableRes val iconRes: Int, @StringRes val desc: Int) {
    PIC_LOGO(R.drawable.ic_pic_title_logo, R.string.login_intro_title),
    BACK(R.drawable.ic_back, R.string.go_back),
    PLUS(R.drawable.ic_plus, R.string.go_plus),
    USER(R.drawable.ic_user, R.string.go_user),
    GROUP_MEMBER(R.drawable.ic_group_member, R.string.go_group_member),
    ;
}
