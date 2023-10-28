package com.emreozcelik.littlelemon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple700 = Color(0xFFEE9972)

private val lightColorPalette = lightColors(
    primary = LittleLemonColor.yellow,
    primaryVariant = Purple700,
    secondary = LittleLemonColor.pink
)

@Composable
fun LittleLemonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}