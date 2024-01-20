package com.cryptospark.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cryptospark.R

val appFontFamily = FontFamily(
    fonts = listOf(
        Font(
            R.font.solway_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        Font(
            R.font.solway_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            R.font.solway_medium,
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        ),
        Font(
            R.font.solway_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        Font(
            R.font.solway_extrabold,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Normal
        )
    )
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        letterSpacing = 1.15.sp
    ),
    h2 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 1.15.sp
    ),
    h3 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 1.15.sp
    ),
    caption = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 1.15.sp
    )
)