package com.marcushurlbut.demusicfy.ui.view

import ChordFinder
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcushurlbut.demusicfy.domain.data.database.AppDatabase
import com.marcushurlbut.demusicfy.ui.view.chordfinder.profile.ChordProfiles
import com.marcushurlbut.demusicfy.ui.view.metronome.Metronome
import com.marcushurlbut.demusicfy.ui.view.metronome.profile.MetronomeProfiles
import com.marcushurlbut.demusicfy.ui.view.metronome.MetronomeSounds
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeProfilesViewModel
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object ChordFinder
@Serializable
object ChordProfiles
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
    drawerState: DrawerState,
    chordFinderViewModel: ChordFinderViewModel
) {
    val metronomeViewModel: MetronomeViewModel = remember { MetronomeViewModel() }
    val metronomeProfileViewModel: MetronomeProfilesViewModel = remember { MetronomeProfilesViewModel(database.metronomeProfileDAO()) }

    val chordFinderUiState = chordFinderViewModel.state.collectAsState()
    val metronomeUiState = metronomeViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = startDestination) {
        composable<WelcomeScreen> {
            AnimatedVisibility(visible = true) {
                WelcomeScreen(
                    onNavigateToChordFinder =  { navController.navigate(route = ChordFinder) },
                    onNavigateToMetronome = { navController.navigate(route = Metronome) },
                    drawerState = drawerState
                )
            }
        }
        composable<ChordFinder> {
            AnimatedVisibility(visible = true) {
                ChordFinder(
                    state = chordFinderUiState,
                    onFormAction = chordFinderViewModel::formAction,
                    onClearNotes = chordFinderViewModel::clearNotes,
                    onOpenForm = chordFinderViewModel::openForm,
                    onCloseForm = chordFinderViewModel::closeForm,
                    onPressFret = chordFinderViewModel::pressFret,
                    onNicknameValueChanged = chordFinderViewModel::onNicknameValueChanged,
                    onNavigateToChordProfiles = { navController.navigate(route = ChordProfiles) },
                    drawerState = drawerState,
                )
            }
        }
        composable<ChordProfiles> {
            AnimatedVisibility(visible = true) {
                ChordProfiles(
                    state = chordFinderUiState,
                    drawerState = drawerState,
                    onPopBackstack = navController::popBackStack,
                    onOpenForm = chordFinderViewModel::openForm,
                    onToggleDeleteMode = chordFinderViewModel::toggleDeleteMode,
                    onToggleEditMode = chordFinderViewModel::toggleEditMode,
                    onListProfiles = chordFinderViewModel::listProfiles,
                    onFormAction = chordFinderViewModel::formAction,
                    onCloseForm = chordFinderViewModel::closeForm,
                    onSetProfileFromDatabase = chordFinderViewModel::setProfileFromDatabase,
                    onNicknameValueChanged = chordFinderViewModel::onNicknameValueChanged,
                )
            }
        }
        composable<Metronome> {
            AnimatedVisibility(visible = true) {
                Metronome(
                    viewModel = metronomeViewModel,
                    drawerState = drawerState,
                    onNavigateToMetronomeProfiles = { navController.navigate(route = MetronomeProfiles) },
                    onNavigateToMetronomeSounds = { navController.navigate(route = MetronomeSounds) }
                )
            }
        }
        composable<MetronomeProfiles> {
            AnimatedVisibility(visible = true) {
                MetronomeProfiles(
                    viewModel = metronomeProfileViewModel,
                    drawerState = drawerState,
                    onPopBackstack = navController::popBackStack,
                )
            }
        }
        composable<MetronomeSounds> {
            AnimatedVisibility(visible = true) {
                MetronomeSounds(
                    state = metronomeUiState,
                    onSwitchSound = metronomeViewModel::switchSound,
                    drawerState = drawerState,
                    onPopBackstack = navController::popBackStack
                )
            }
        }
    }
}