package com.marcushurlbut.demusicfy.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import demusicfy.composeapp.generated.resources.Pacifico_Regular
import demusicfy.composeapp.generated.resources.BerkshireSwash_Regular
import demusicfy.composeapp.generated.resources.Mitr_Bold
import demusicfy.composeapp.generated.resources.Mitr_ExtraLight
import demusicfy.composeapp.generated.resources.Mitr_Light
import demusicfy.composeapp.generated.resources.Mitr_Medium
import demusicfy.composeapp.generated.resources.Mitr_Regular
import demusicfy.composeapp.generated.resources.Mitr_SemiBold
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
fun MitrFontFamily() : FontFamily {
    return FontFamily(
        Font(Res.font.Mitr_Light, FontWeight.Light),
        Font(Res.font.Mitr_ExtraLight, FontWeight.ExtraLight),
        Font(Res.font.Mitr_Regular, FontWeight.Normal),
        Font(Res.font.Mitr_Regular, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.Mitr_Medium, FontWeight.Medium),
        Font(Res.font.Mitr_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Mitr_Bold, FontWeight.Bold),
    )
}
