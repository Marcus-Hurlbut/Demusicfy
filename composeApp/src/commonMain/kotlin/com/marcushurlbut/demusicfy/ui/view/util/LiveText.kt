package com.marcushurlbut.demusicfy.ui.view.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle

@Composable
fun LiveText(
    text: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    spec: AnimationSpec<Int> = tween(durationMillis = text.length * 120, easing = LinearEasing),
    style: TextStyle = LocalTextStyle.current,
    preoccupySpace: Boolean = true
) {
    var textToAnimate by remember { mutableStateOf("") }

    val index = remember {
        Animatable(initialValue = 0, typeConverter = Int.VectorConverter)
    }

    // Effect to handle animation when visibility changes
    LaunchedEffect(isVisible) {
        if (isVisible) {
            textToAnimate = text
            index.animateTo(text.length, spec)
        } else {
            index.snapTo(0)
        }
    }

    // Effect to handle animation when text content changes
    LaunchedEffect(text) {
        if (isVisible) {
            index.snapTo(0)
            textToAnimate = text
            index.animateTo(text.length, spec)
        }
    }

    // Box composable to contain the animated and static text
    Box(modifier = modifier) {
        if (preoccupySpace && index.isRunning) {
            Text(
                text = text,
                style = style,
                modifier = Modifier.alpha(0f)
            )
        }

        // Display animated text from current index value
        Text(
            text = textToAnimate.substring(0, index.value),
            style = style
        )
    }
}