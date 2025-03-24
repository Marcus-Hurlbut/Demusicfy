package com.marcushurlbut.demusicfy.ui.view

import ChordFinder
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcushurlbut.demusicfy.domain.data.database.AppDatabase
import com.marcushurlbut.demusicfy.domain.data.model.MetronomeProfile
import com.marcushurlbut.demusicfy.resource.AddIcon
import com.marcushurlbut.demusicfy.resource.ClearIcon
import com.marcushurlbut.demusicfy.resource.DeleteIcon
import com.marcushurlbut.demusicfy.resource.EQIcon
import com.marcushurlbut.demusicfy.resource.EditIcon
import com.marcushurlbut.demusicfy.resource.OpenFolderIcon
import com.marcushurlbut.demusicfy.resource.PauseIcon
import com.marcushurlbut.demusicfy.resource.PlayIcon
import com.marcushurlbut.demusicfy.resource.SaveIcon
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppBar
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppContainer
import com.marcushurlbut.demusicfy.ui.view.appmenu.BottomMenuBar
import com.marcushurlbut.demusicfy.ui.view.metronome.Metronome
import com.marcushurlbut.demusicfy.ui.view.metronome.MetronomeProfiles
import com.marcushurlbut.demusicfy.ui.view.metronome.MetronomeSounds
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeProfilesViewModel
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object ChordFinder
@Serializable
object Metronome
@Serializable
object MetronomeProfiles
@Serializable
object MetronomeSounds
@Serializable
object WelcomeScreen

@Composable
fun NavGraph(
    database: AppDatabase,
    navController: NavHostController,
    startDestination: Any,
    drawerState: DrawerState
) {
    val chordFinderViewModel: ChordFinderViewModel = remember { ChordFinderViewModel() }
    val metronomeViewModel: MetronomeViewModel = remember { MetronomeViewModel() }
    val metronomeProfileViewModel: MetronomeProfilesViewModel = remember { MetronomeProfilesViewModel(database.metronomeProfileDAO()) }

    val primaryColor = MaterialTheme.colorScheme.primary
    val navButtonSize = Modifier.size(28.dp)

    NavHost(navController = navController, startDestination = startDestination) {
        composable<WelcomeScreen> {
            AnimatedVisibility(visible = true) {
                Scaffold(
                    topBar = {
                        AppBar(drawerState)
                    }
                ) { innerPadding ->
                    AppContainer(containerPadding = innerPadding)
                    {
                        WelcomeScreen(
                            onNavigateToChordFinder =  { navController.navigate(route = ChordFinder) },
                            onNavigateToMetronome = { navController.navigate(route = Metronome) }
                        )
                    }
                }
            }
        }
        composable<ChordFinder> {
            AnimatedVisibility(visible = true) {
                Scaffold(
                    topBar = {
                        AppBar(drawerState)
                    },
                    bottomBar = {
                        BottomMenuBar(
                            onFirstButtonClick = { chordFinderViewModel.clearNotes() },
                            onSecondButtonClick = { },
                            onThirdButtonClick = { },
                            onFourthButtonClick = { },
                            firstButtonIcon = { ClearIcon(primaryColor, modifier = navButtonSize) },
                            secondButtonIcon = { SaveIcon(primaryColor, modifier = navButtonSize) },
                            thirdButtonIcon = { OpenFolderIcon(primaryColor, modifier = navButtonSize) },
                        )
                    }
                ) { innerPadding ->
                    AppContainer(containerPadding = innerPadding)
                    {
                        ChordFinder(viewModel = chordFinderViewModel)
                    }
                }
            }
        }
        composable<Metronome> {
            AnimatedVisibility(visible = true) {
                Scaffold(
                    topBar = {
                        AppBar(drawerState)
                    },
                    bottomBar = {
                        BottomMenuBar(
                            onFirstButtonClick = { metronomeViewModel.play() },
                            onSecondButtonClick = { metronomeViewModel.stop() },
                            onThirdButtonClick = { navController.navigate(route = MetronomeSounds)},
                            onFourthButtonClick = { navController.navigate(route = MetronomeProfiles) },
                            firstButtonIcon = { PlayIcon(primaryColor, modifier = navButtonSize) },
                            secondButtonIcon = { PauseIcon(primaryColor, modifier = navButtonSize) },
                            thirdButtonIcon = { EQIcon(primaryColor, modifier = navButtonSize) },
                            fourthButtonIcon = { OpenFolderIcon(primaryColor, modifier = navButtonSize) },
                        )
                    }
                ) { innerPadding ->
                    AppContainer(containerPadding = innerPadding,)
                    {
                        Metronome(
                            navController = navController,
                            viewModel = metronomeViewModel
                        )
                    }
                }
            }
        }
        composable<MetronomeProfiles> {
            AnimatedVisibility(visible = true) {
                Scaffold(
                    topBar = {
                        AppBar(drawerState)
                    },
                    bottomBar = {
                        BottomMenuBar(
                            onFirstButtonClick = { metronomeProfileViewModel.toggleDeleteMode() },
                            onSecondButtonClick = {
                                val formProfile = MetronomeProfile(song = "", artist="", bpm = 0)
                                metronomeProfileViewModel.openForm(formProfile)
                            },
                            onThirdButtonClick = { metronomeProfileViewModel.toggleEditMode() },
                            onFourthButtonClick = { },
                            firstButtonIcon = { DeleteIcon(primaryColor, modifier = navButtonSize) },
                            secondButtonIcon = { AddIcon(primaryColor, modifier = navButtonSize) },
                            thirdButtonIcon = { EditIcon(primaryColor, modifier = navButtonSize) },
                        )
                    }
                ) { innerPadding ->
                    AppContainer(containerPadding = innerPadding)
                    {
                        MetronomeProfiles(
                            navController = navController,
                            viewModel = metronomeProfileViewModel
                        )
                    }
                }
            }
        }
        composable<MetronomeSounds> {
            AnimatedVisibility(visible = true) {
                Scaffold(
                    topBar = {
                        AppBar(drawerState)
                    }
                ) { innerPadding ->
                    AppContainer(containerPadding = innerPadding)
                    {
                        MetronomeSounds(
                            navController = navController,
                            viewModel = metronomeViewModel
                        )
                    }
                }
            }
        }
    }
}