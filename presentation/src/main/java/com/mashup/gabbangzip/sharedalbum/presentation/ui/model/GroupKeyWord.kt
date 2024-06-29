package com.mashup.gabbangzip.sharedalbum.presentation.ui.model

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

enum class GroupKeyWord{
    SCHOOL,
    COMPANY,
    CREW,
    NETWORK,
    EXERCISE,
    HOBBY,
    LITTLE_MOIM,
    ;

    companion object {
        fun getSymbol(keyword: GroupKeyWord): Int {
            return when (keyword) {
                SCHOOL -> R.drawable.sb_school
                COMPANY -> R.drawable.sb_company
                CREW -> R.drawable.sb_crew
                NETWORK -> R.drawable.sb_network
                EXERCISE -> R.drawable.sb_exercise
                HOBBY -> R.drawable.sb_hobby
                LITTLE_MOIM -> R.drawable.sb_little_moim
            }
        }

        fun getBackgroundColor(keyword: GroupKeyWord): Color {
            return when (keyword) {
                SCHOOL -> ConiferAlpha30
                COMPANY -> MagentaPinkAlpha30
                CREW -> MayaBlueAlpha30
                NETWORK -> CoralAlpha30
                EXERCISE -> DandelionAlpha30
                HOBBY -> MalibuAlpha30
                LITTLE_MOIM -> LavenderAlpha30
            }
        }
    }
}
