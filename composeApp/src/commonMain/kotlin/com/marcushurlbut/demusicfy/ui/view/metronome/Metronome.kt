package com.marcushurlbut.demusicfy.ui.view.metronome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.resource.EQIcon
import com.marcushurlbut.demusicfy.resource.OpenFolderIcon
import com.marcushurlbut.demusicfy.resource.PauseIcon
import com.marcushurlbut.demusicfy.resource.PlayIcon
import com.marcushurlbut.demusicfy.ui.view.MetronomeProfiles
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppContainer
import com.marcushurlbut.demusicfy.ui.view.metronome.components.BackgroundArt
import com.marcushurlbut.demusicfy.ui.view.metronome.components.BpmAdjusterInterface
import com.marcushurlbut.demusicfy.ui.view.metronome.components.BpmSlider
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel

@Composable
fun Metronome(
    viewModel: MetronomeViewModel,
    drawerState: DrawerState,
    onNavigateToMetronomeProfiles: () -> Unit,
    onNavigateToMetronomeSounds: () -> Unit,

) {
    val state by viewModel.uiState.collectAsState()
    val primaryColor = MaterialTheme.colorScheme.primary
    val navButtonSize = Modifier.size(28.dp)

    AppContainer(
        onFirstButtonClick = { viewModel.play() },
        onSecondButtonClick = { viewModel.stop() },
        onThirdButtonClick = onNavigateToMetronomeSounds,
        onFourthButtonClick = onNavigateToMetronomeProfiles,
        firstButtonIcon = { PlayIcon(primaryColor, modifier = navButtonSize) },
        secondButtonIcon = { PauseIcon(primaryColor, modifier = navButtonSize) },
        thirdButtonIcon = { EQIcon(primaryColor, modifier = navButtonSize) },
        fourthButtonIcon = { OpenFolderIcon(primaryColor, modifier = navButtonSize) },
        drawerState = drawerState
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            BackgroundArt()
            BpmAdjusterInterface(viewModel, state)
            Spacer(modifier = Modifier.weight(1f))
            BpmSlider(viewModel, state)
        }
    }
}