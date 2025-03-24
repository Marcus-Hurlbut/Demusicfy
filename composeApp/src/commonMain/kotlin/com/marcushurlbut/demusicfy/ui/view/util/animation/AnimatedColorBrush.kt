package com.marcushurlbut.demusicfy.ui.view.util.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

@Composable
fun AnimatedColorBrush(
    initialValue: Color,
    targetValue: Color,
    durationMillis: Int
) : Brush {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(tween(durationMillis), RepeatMode.Reverse),
        label = "color"
    )

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(animatedColor, animatedColor),
        startX = 0.0f,
        endX = 500.0f,
        tileMode = TileMode.Clamp
    )

    return gradientBrush
}