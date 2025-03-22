package com.marcushurlbut.demusicfy.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import demusicfy.composeapp.generated.resources.Pacifico_Regular
import demusicfy.composeapp.generated.resources.BerkshireSwash_Regular
import demusicfy.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun CursiveFontFamily() : FontFamily {
    return FontFamily(
        Font(Res.font.Pacifico_Regular, FontWeight.Light),
        Font(Res.font.Pacifico_Regular, FontWeight.Normal),
        Font(Res.font.Pacifico_Regular, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.Pacifico_Regular, FontWeight.Medium),
        Font(Res.font.Pacifico_Regular, FontWeight.Bold)
    )
}

@Composable
fun BerkshireFontFamily() : FontFamily {
    return FontFamily(
        Font(Res.font.BerkshireSwash_Regular, FontWeight.Light),
        Font(Res.font.BerkshireSwash_Regular, FontWeight.Normal),
        Font(Res.font.BerkshireSwash_Regular, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.BerkshireSwash_Regular, FontWeight.Medium),
        Font(Res.font.BerkshireSwash_Regular, FontWeight.Bold)
    )
}
