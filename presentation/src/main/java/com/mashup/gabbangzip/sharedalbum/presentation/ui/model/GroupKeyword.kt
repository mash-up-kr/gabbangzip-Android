package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Conifer
import com.mashup.gabbangzip.sharedalbum.presentation.theme.ConiferAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.ConiferAlpha40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Coral
import com.mashup.gabbangzip.sharedalbum.presentation.theme.CoralAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.CoralAlpha40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Dandelion
import com.mashup.gabbangzip.sharedalbum.presentation.theme.DandelionAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.DandelionAlpha40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Lavender
import com.mashup.gabbangzip.sharedalbum.presentation.theme.LavenderAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.LavenderAlpha40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPink
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPinkAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPinkAlpha40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Malibu
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MalibuAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MalibuAlpha40
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlue
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlueAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlueAlpha40

enum class GroupKeyword(
    @DrawableRes val symbolResId: Int,
    val backgroundColor: Color,
    val frameInnerColor: Color,
    val symbolColor: Color,
    @StringRes val tagNameResId: Int,
) {
    SCHOOL(
        symbolResId = R.drawable.sb_school,
        backgroundColor = ConiferAlpha30,
        frameInnerColor = ConiferAlpha40,
        symbolColor = Conifer,
        tagNameResId = R.string.tag_school,
    ),
    CREW(
        symbolResId = R.drawable.sb_crew,
        backgroundColor = MayaBlueAlpha30,
        frameInnerColor = MayaBlueAlpha40,
        symbolColor = MayaBlue,
        tagNameResId = R.string.tag_crew,
    ),
    COMPANY(
        symbolResId = R.drawable.sb_company,
        backgroundColor = MagentaPinkAlpha30,
        frameInnerColor = MagentaPinkAlpha40,
        symbolColor = MagentaPink,
        tagNameResId = R.string.tag_company,
    ),
    LITTLE_MOIM(
        symbolResId = R.drawable.sb_little_moim,
        backgroundColor = LavenderAlpha30,
        frameInnerColor = LavenderAlpha40,
        symbolColor = Lavender,
        tagNameResId = R.string.tag_somoim,
    ),
    NETWORK(
        symbolResId = R.drawable.sb_network,
        backgroundColor = CoralAlpha30,
        frameInnerColor = CoralAlpha40,
        symbolColor = Coral,
        tagNameResId = R.string.tag_network,
    ),
    EXERCISE(
        symbolResId = R.drawable.sb_exercise,
        backgroundColor = DandelionAlpha30,
        frameInnerColor = DandelionAlpha40,
        symbolColor = Dandelion,
        tagNameResId = R.string.tag_exercise,
    ),
    HOBBY(
        symbolResId = R.drawable.sb_hobby,
        backgroundColor = MalibuAlpha30,
        frameInnerColor = MalibuAlpha40,
        symbolColor = Malibu,
        tagNameResId = R.string.tag_hobby,
    ),
    ;

    companion object {
        fun GroupKeyword.getFrame() = when (this) {
            SCHOOL -> PicPhotoFrame.SNOWMAN
            CREW -> PicPhotoFrame.HAMBURGER
            COMPANY -> PicPhotoFrame.GHOST
            LITTLE_MOIM -> PicPhotoFrame.CLOVER
            NETWORK -> PicPhotoFrame.PLUS
            EXERCISE -> PicPhotoFrame.SEXY
            HOBBY -> PicPhotoFrame.FLOWER
        }

        fun getKeyword(keyword: String): GroupKeyword {
            return entries.associateBy { it.name }[keyword] ?: SCHOOL
        }
    }
}
