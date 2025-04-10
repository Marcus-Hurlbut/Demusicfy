package com.marcushurlbut.demusicfy.ui.view.metronome

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.resource.BackArrowIOS
import com.marcushurlbut.demusicfy.resource.CheckBoxBlankIcon
import com.marcushurlbut.demusicfy.resource.CheckBoxFilledIcon
import com.marcushurlbut.demusicfy.resource.MitrFontFamily
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppContainer
import com.marcushurlbut.demusicfy.ui.view.util.animation.AnimatedColorBrush
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel

@Composable
fun MetronomeSounds(
    state: State<MetronomeUiState>,
    onSwitchSound: (String) -> Unit,
    drawerState: DrawerState,
    onPopBackstack: () -> Unit
) {
    AppContainer(
        visibleBottomBar = false,
        drawerState = drawerState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Box(Modifier.fillMaxWidth().fillMaxHeight(.08f)) {
                    IconButton(
                        onClick = onPopBackstack,
                        content = { BackArrowIOS(MaterialTheme.colorScheme.onPrimaryContainer) },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 0.dp, y = 5.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            "Metronome Sounds",
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }

            ElevatedCard {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(.85f)
                        .fillMaxHeight(.70f)
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 2.dp,
                            brush = AnimatedColorBrush(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary,
                                5000
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    items(count = state.value.soundManager.sounds.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = state.value.soundManager.sounds[index].replace("_", " ")
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                                fontSize = 20.sp,
                                fontFamily = MitrFontFamily(),
                                modifier = Modifier.padding(4.dp).padding(start = 20.dp)
                            )
                            Spacer(Modifier.weight(1f))
                            IconButton(
                                onClick = { onSwitchSound(state.value.soundManager.sounds[index]) },
                                content = {
                                    if (state.value.soundManager.sounds[index] == state.value.currentSound) {
                                        CheckBoxFilledIcon(tint = MaterialTheme.colorScheme.onSecondaryContainer)
                                    } else {
                                        CheckBoxBlankIcon(tint = MaterialTheme.colorScheme.onSecondaryContainer)
                                    }
                                },
                                modifier = Modifier.padding(end = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}