package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import androidx.annotation.DrawableRes
import com.mashup.gabbangzip.sharedalbum.presentation.R

enum class PicPhotoFrame(
    val keyword: String,
    @DrawableRes val frameResId: Int,
) {
    SNOWMAN(
        keyword = GroupKeyword.SCHOOL.name,
        frameResId = R.drawable.frame_snowman,
    ),
    CLOVER(
        keyword = GroupKeyword.LITTLE_MOIM.name,
        frameResId = R.drawable.frame_clover,
    ),
    FLOWER(
        keyword = GroupKeyword.HOBBY.name,
        frameResId = R.drawable.frame_flower,
    ),
    GHOST(
        keyword = GroupKeyword.COMPANY.name,
        frameResId = R.drawable.frame_ghost,
    ),
    PLUS(
        keyword = GroupKeyword.NETWORK.name,
        frameResId = R.drawable.frame_plus,
    ),
    SEXY(
        keyword = GroupKeyword.EXERCISE.name,
        frameResId = R.drawable.frame_sexy,
    ),
    HAMBURGER(
        keyword = GroupKeyword.CREW.name,
        frameResId = R.drawable.frame_hamburger,
    ),
    ;

    companion object {
        fun getType(frameType: String): PicPhotoFrame {
            return entries.associateBy { it.name }[frameType] ?: PLUS
        }

        fun getFrontType(keyword: String): PicPhotoFrame {
            return entries.associateBy { it.keyword }[keyword] ?: PLUS
        }
    }
}
