package com.cryptospark.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = lightColors(
    primary = primaryDarkColor,
    secondary = secondaryColor,
    background = primaryDarkColor,
    surface = primaryColor,
    onPrimary = secondaryLightColor,
    onSecondary = secondaryTextColor,
    onBackground = primaryColor,
    onSurface = primaryColor
)

private val LightColorPalette = darkColors(
    primary = primaryColor,
    secondary = secondaryLightColor,
    background = secondaryColor,
    surface = primaryLightColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = primaryColor
)
@Composable
fun CryptoSparkComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}