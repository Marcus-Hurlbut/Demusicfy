package com.marcushurlbut.demusicfy.ui.view.metronome.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.resource.MetronomeImage

@Composable
fun BackgroundArt() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = MetronomeImage(),
            contentDescription = "",
        )
    }
}