package com.mashup.gabbangzip.sharedalbum.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mashup.gabbangzip.sharedalbum.presentation.R

internal val pretendard = FontFamily(
    Font(R.font.pretendard_black, FontWeight.Black),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
)

@Immutable
data class PretendardTypography(
    val headBold20: TextStyle,
    val headBold18: TextStyle,
    val headBold16: TextStyle,
    val headBold14: TextStyle,
    val bodyMedium17: TextStyle,
    val bodyMedium16: TextStyle,
    val bodyMedium14: TextStyle,
    val bodyMedium12: TextStyle,
    val textNormal14: TextStyle,
    val captionNormal12: TextStyle,
)

internal val PicTypography = PretendardTypography(
    headBold20 = pretendardTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20,
    ),
    headBold18 = pretendardTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18,
    ),
    headBold16 = pretendardTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16,
    ),
    headBold14 = pretendardTextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14,
    ),
    bodyMedium17 = pretendardTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17,
    ),
    bodyMedium16 = pretendardTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16,
    ),
    bodyMedium14 = pretendardTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14,
    ),
    bodyMedium12 = pretendardTextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12,
    ),
    textNormal14 = pretendardTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14,
    ),
    captionNormal12 = pretendardTextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12,
    ),
)

private fun pretendardTextStyle(
    fontSize: Int,
    fontWeight: FontWeight,
//    lineHeight: Int,
) = TextStyle(
    fontFamily = pretendard,
    fontWeight = fontWeight,
    fontSize = fontSize.sp,
//    lineHeight = lineHeight.sp,
)
