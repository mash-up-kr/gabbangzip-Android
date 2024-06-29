package com.mashup.gabbangzip.sharedalbum.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.ConiferAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.CoralAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.DandelionAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.LavenderAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MagentaPinkAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MalibuAlpha30
import com.mashup.gabbangzip.sharedalbum.presentation.theme.MayaBlueAlpha30

enum class GroupKeyWord(
    @DrawableRes val symbolResId: Int,
    val backgroundColor: Color,
) {
    SCHOOL(
        symbolResId = R.drawable.sb_school,
        backgroundColor = ConiferAlpha30,
    ),
    COMPANY(
        symbolResId = R.drawable.sb_company,
        backgroundColor = MagentaPinkAlpha30,
    ),
    CREW(
        symbolResId = R.drawable.sb_crew,
        backgroundColor = MayaBlueAlpha30,
    ),
    NETWORK(
        symbolResId = R.drawable.sb_network,
        backgroundColor = CoralAlpha30,
    ),
    EXERCISE(
        symbolResId = R.drawable.sb_exercise,
        backgroundColor = DandelionAlpha30,
    ),
    HOBBY(
        symbolResId = R.drawable.sb_hobby,
        backgroundColor = MalibuAlpha30,
    ),
    LITTLE_MOIM(
        symbolResId = R.drawable.sb_little_moim,
        backgroundColor = LavenderAlpha30,
    ),
    ;
}
