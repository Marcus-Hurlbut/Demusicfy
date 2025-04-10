package com.marcushurlbut.demusicfy.ui.view.metronome.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel

@Composable
fun BpmSlider(
    viewModel: MetronomeViewModel,
    state: MetronomeUiState
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.Bottom
    ) {
        Slider(
            modifier = Modifier,
            enabled = true,
            value = state.bpm.toFloat(),
            onValueChange = { viewModel.setBPM(it.toInt()) },
            valueRange = 0f..228f,
            steps = 57,
            onValueChangeFinished = {}
        )
    }
}