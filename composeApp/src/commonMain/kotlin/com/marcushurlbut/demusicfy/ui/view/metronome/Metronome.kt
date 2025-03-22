package com.marcushurlbut.demusicfy.ui.view.metronome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.domain.data.dao.MetronomeProfileDAO
import com.marcushurlbut.demusicfy.resource.AddIcon
import com.marcushurlbut.demusicfy.resource.MetronomeImage
import com.marcushurlbut.demusicfy.resource.SubtractIcon
import com.marcushurlbut.demusicfy.ui.view.metronome.components.MetronomeActionBar
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel

@Composable
fun Metronome(
    navController: NavHostController,
    viewModel: MetronomeViewModel
) {
//    val viewModel: MetronomeViewModel = remember { MetronomeViewModel() }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 128.dp, bottom = 128.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            IconButton(
                onClick = { viewModel.decrementBPM() },
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
                ,
                content = { SubtractIcon() }
            )

            Text(
                text = uiState.bpm.toString(),
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

        Spacer(modifier = Modifier.weight(1f))
        MetronomeActionBar(navController, viewModel)

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
                value = uiState.bpm.toFloat(),
                onValueChange = { viewModel.setBPM(it.toInt())},
                valueRange = 0f..228f,
                steps = 57,
                onValueChangeFinished = {}
            )
        }
    }
}