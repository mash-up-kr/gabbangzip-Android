package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Conifer
import com.mashup.gabbangzip.sharedalbum.presentation.theme.ConiferAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Coral
import com.mashup.gabbangzip.sharedalbum.presentation.theme.CoralAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Dandelion
import com.mashup.gabbangzip.sharedalbum.presentation.theme.DandelionAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Lavender
import com.mashup.gabbangzip.sharedalbum.presentation.theme.LavenderAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPink
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPinkAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Malibu
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MalibuAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlue
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlueAlpha30

enum class GroupKeyword(
    @DrawableRes val symbolResId: Int,
    val backgroundColor: Color,
    val symbolColor: Color,
    @StringRes val tagNameResId: Int,
    val frame: PicPhotoFrame,
) {
    SCHOOL(
        symbolResId = R.drawable.sb_school,
        backgroundColor = ConiferAlpha30,
        symbolColor = Conifer,
        tagNameResId = R.string.tag_school,
        frame = PicPhotoFrame.SNOWMAN,
    ),
    CREW(
        symbolResId = R.drawable.sb_crew,
        backgroundColor = MayaBlueAlpha30,
        symbolColor = MayaBlue,
        tagNameResId = R.string.tag_crew,
        frame = PicPhotoFrame.HAMBURGER,
    ),
    COMPANY(
        symbolResId = R.drawable.sb_company,
        backgroundColor = MagentaPinkAlpha30,
        symbolColor = MagentaPink,
        tagNameResId = R.string.tag_company,
        frame = PicPhotoFrame.GHOST,
    ),
    LITTLE_MOIM(
        symbolResId = R.drawable.sb_little_moim,
        backgroundColor = LavenderAlpha30,
        symbolColor = Lavender,
        tagNameResId = R.string.tag_somoim,
        frame = PicPhotoFrame.CLOVER,
    ),
    NETWORK(
        symbolResId = R.drawable.sb_network,
        backgroundColor = CoralAlpha30,
        symbolColor = Coral,
        tagNameResId = R.string.tag_network,
        frame = PicPhotoFrame.PLUS,
    ),
    EXERCISE(
        symbolResId = R.drawable.sb_exercise,
        backgroundColor = DandelionAlpha30,
        symbolColor = Dandelion,
        tagNameResId = R.string.tag_exercise,
        frame = PicPhotoFrame.SEXY,
    ),
    HOBBY(
        symbolResId = R.drawable.sb_hobby,
        backgroundColor = MalibuAlpha30,
        symbolColor = Malibu,
        tagNameResId = R.string.tag_hobby,
        frame = PicPhotoFrame.FLOWER,
    ),
    ;

    companion object {
        fun getKeyword(keyword: String): GroupKeyword {
            return entries.associateBy { it.name }[keyword] ?: SCHOOL
        }
    }
}
