package com.mashup.gabbangzip.sharedalbum.presentation.ui.vote.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Conifer
import com.mashup.gabbangzip.sharedalbum.presentation.theme.ConiferAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Coral
import com.mashup.gabbangzip.sharedalbum.presentation.theme.CoralAlpha30

enum class PhotoVoteType(
    val id: Int,
    @StringRes val imageDescId: Int,
    @ColorRes val mainColor: Color,
    @ColorRes val alphaColor: Color,
    @DrawableRes val imageResId: Int,
) {
    DEFAULT(
        id = 0,
        imageDescId = -1,
        mainColor = Color.Transparent,
        alphaColor = Color.Transparent,
        imageResId = -1,
    ),
    DISLIKE(
        id = 2,
        imageDescId = R.string.vote_dislike_desc,
        mainColor = Coral,
        alphaColor = CoralAlpha30,
        imageResId = R.drawable.ic_dislike,
    ),
    LIKE(
        id = 1,
        imageDescId = R.string.vote_like_desc,
        mainColor = Conifer,
        alphaColor = ConiferAlpha30,
        imageResId = R.drawable.ic_like,
    ),
    ;
}
