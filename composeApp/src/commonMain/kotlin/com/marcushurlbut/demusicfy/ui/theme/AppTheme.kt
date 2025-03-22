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
            primary = Color(0xFFCC33FF),    // Purple
            secondary = Color(0xFFCB7DE3),  // Lighter Purple
            tertiary = Color(0xFFCC9602) ,   // Gold
            background = Color(0xFF121212), // Almost Black
            surface = Color(0xFF1A1A1A), // Very dark gray
            onPrimary = Color.Black,
            onSecondary = Color.Black,
            onBackground = Color.White,
            onSurface = Color.White,

            primaryContainer = Color(0xFF372838), // Dark Gray Purple
            onPrimaryContainer = Color.White
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
