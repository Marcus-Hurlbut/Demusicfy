package com.marcushurlbut.demusicfy.ui.theme

import androidx.compose.runtime.Composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

class AppTheme {
    companion object {
        val lightPalette = lightColorScheme(
            primary = Color(0xFFCC33FF),    // Purple
            secondary = Color(0xFFCB7DE3),  // Lighter Purple
            tertiary = Color(0xFFCC9602),   // Gold
            background = Color(0xFFFFFFFF), // White
            surface = Color(0xFFE3E1E3),  // Off White
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = Color.Black,
            onSurface = Color.Black,

            primaryContainer = Color(0xFFE5C0FA), // Very Light Purple

            onPrimaryContainer = Color.Black

        )

        val darkPalette = darkColorScheme(
            surface = Color(0xFF1A1A1A), // Very dark gray
            onSurface = Color.White,

            background = Color(0xFF121212), // Almost Black
            onBackground = Color.White,

            primary = Color(0xFFCC33FF),    // Purple
            onPrimary = Color.Black,
            primaryContainer = Color(0xFF543A69), // Dark Purple
            onPrimaryContainer = Color.White,

            secondary = Color(0xFF4F4099),  // Lighter Blue Purple
            onSecondary = Color.Black,
            secondaryContainer = Color(0xFF312766), // Navy Bluish Purple
            onSecondaryContainer = Color.White,


            tertiary = Color(0xFFA84C55),   // Pastel Red
            onTertiary = Color.White,
            tertiaryContainer = Color(0xFFE86D79), // Lighter Pastel Red
            onTertiaryContainer = Color.White
        )

        val fretboard = Color(0xFF4E3629)
    }
}

@Composable
fun AppTheme(
    isDarkMode: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (isDarkMode) AppTheme.darkPalette else AppTheme.lightPalette

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
