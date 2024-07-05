package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.ConiferAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.CoralAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.DandelionAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.LavenderAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPinkAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MalibuAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlueAlpha30

enum class GroupKeyword(
    @DrawableRes val symbolResId: Int,
    val backgroundColor: Color,
    @StringRes val tagNameResId: Int,
) {
    SCHOOL(
        symbolResId = R.drawable.sb_school,
        backgroundColor = ConiferAlpha30,
        tagNameResId = R.string.tag_school,
    ),
    COMPANY(
        symbolResId = R.drawable.sb_company,
        backgroundColor = MagentaPinkAlpha30,
        tagNameResId = R.string.tag_company,
    ),
    CREW(
        symbolResId = R.drawable.sb_crew,
        backgroundColor = MayaBlueAlpha30,
        tagNameResId = R.string.tag_crew,
    ),
    NETWORK(
        symbolResId = R.drawable.sb_network,
        backgroundColor = CoralAlpha30,
        tagNameResId = R.string.tag_network,
    ),
    EXERCISE(
        symbolResId = R.drawable.sb_exercise,
        backgroundColor = DandelionAlpha30,
        tagNameResId = R.string.tag_exercise,
    ),
    HOBBY(
        symbolResId = R.drawable.sb_hobby,
        backgroundColor = MalibuAlpha30,
        tagNameResId = R.string.tag_hobby,
    ),
    LITTLE_MOIM(
        symbolResId = R.drawable.sb_little_moim,
        backgroundColor = LavenderAlpha30,
        tagNameResId = R.string.tag_somoim,
    ),
    ;

    companion object {
        fun getKeyword(keyword: String): GroupKeyword {
            return entries.associateBy { it.name }[keyword]
                ?: throw IllegalArgumentException("Invalid keyword")
        }
    }
}
