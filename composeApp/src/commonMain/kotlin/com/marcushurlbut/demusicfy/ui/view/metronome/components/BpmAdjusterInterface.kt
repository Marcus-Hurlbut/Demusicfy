package com.marcushurlbut.demusicfy.ui.view.metronome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.resource.AddIcon
import com.marcushurlbut.demusicfy.resource.SubtractIcon
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel

@Composable
fun BpmAdjusterInterface(
    viewModel: MetronomeViewModel,
    state: MetronomeUiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 128.dp, bottom = 128.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        IconButton(
            onClick = { viewModel.decrementBPM() },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape),
            content = { SubtractIcon() }
        )

        Text(
            text = state.bpm.toString(),
            modifier = Modifier,
            style = MaterialTheme.typography.displayLarge
        )
        IconButton(
            onClick = { viewModel.incrementBPM() },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape),
            content = {
                AddIcon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(48.dp),
                )
            }
        )
    }
}