package com.marcushurlbut.demusicfy.ui.view.metronome.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.domain.data.model.MetronomeProfile
import com.marcushurlbut.demusicfy.resource.AddIcon
import com.marcushurlbut.demusicfy.resource.BackArrowIOS
import com.marcushurlbut.demusicfy.resource.DeleteForeverIcon
import com.marcushurlbut.demusicfy.resource.DeleteIcon
import com.marcushurlbut.demusicfy.resource.EditBoxIcon
import com.marcushurlbut.demusicfy.resource.EditIcon
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppContainer
import com.marcushurlbut.demusicfy.ui.view.util.animation.AnimatedColorBrush
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeProfilesViewModel

@Composable
fun MetronomeProfiles(
//    navController: NavHostController,
    viewModel: MetronomeProfilesViewModel,
    drawerState: DrawerState,
    onPopBackstack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val navButtonSize = Modifier.size(28.dp)
    val color = MaterialTheme.colorScheme.primary

    viewModel.listProfiles()

    AppContainer(
        onFirstButtonClick = { viewModel.toggleDeleteMode() },
        onSecondButtonClick = {
            val formProfile = MetronomeProfile(song = "", artist = "", bpm = 0)
            viewModel.openForm(formProfile)
        },
        onThirdButtonClick = { viewModel.toggleEditMode() },
        firstButtonIcon = { DeleteIcon(color, modifier = navButtonSize) },
        secondButtonIcon = { AddIcon(color, modifier = navButtonSize) },
        thirdButtonIcon = { EditIcon(color, modifier = navButtonSize) },
        drawerState = drawerState
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
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
                            "Metronome Profiles",
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(.10f))

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
                    items(
                        count = uiState.profiles.size,
                    ) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.secondaryContainer),

                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = uiState.profiles[index].song + ", " + uiState.profiles[index].artist,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(4.dp)
                            )

                            Spacer(modifier = Modifier.weight(.35f))
                            Column {
                                Button(
                                    onClick = {},
                                    colors = ButtonColors(
                                        containerColor = MaterialTheme.colorScheme.secondary,
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.secondary,
                                        disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(8.dp),
                                    modifier = Modifier
                                ) {
                                    Text(
                                        uiState.profiles[index].bpm.toString(),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            if (uiState.editMode) {
                                IconButton(
                                    onClick = {
                                        val formProfile = MetronomeProfile(
                                            id = uiState.profiles[index].id,
                                            song = uiState.profiles[index].song,
                                            artist = uiState.profiles[index].artist,
                                            bpm = uiState.profiles[index].bpm
                                        )
                                        viewModel.openForm(formProfile)
                                    },
                                    content = { EditBoxIcon(tint = MaterialTheme.colorScheme.onSurface) }
                                )
                            } else if (uiState.deleteMode) {
                                IconButton(
                                    onClick = {
                                        val formProfile = MetronomeProfile(
                                            id = uiState.profiles[index].id,
                                            song = uiState.profiles[index].song,
                                            artist = uiState.profiles[index].artist,
                                            bpm = uiState.profiles[index].bpm
                                        )
                                        viewModel.openForm(formProfile)
                                    },
                                    content = { DeleteForeverIcon(tint = MaterialTheme.colorScheme.onSurface) }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(.90f))
            ProfileForm(viewModel = viewModel)
        }
    }
}