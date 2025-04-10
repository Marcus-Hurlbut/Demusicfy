package com.marcushurlbut.demusicfy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.RoomDatabase
import com.marcushurlbut.demusicfy.domain.data.database.AppDatabase
import com.marcushurlbut.demusicfy.domain.manager.PreferencesManager
import com.marcushurlbut.demusicfy.ui.theme.AppTheme
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppMenu
import com.marcushurlbut.demusicfy.ui.view.NavGraph
import com.marcushurlbut.demusicfy.ui.view.WelcomeScreen
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel


@Composable
fun App(
    databaseBuilder: RoomDatabase.Builder<AppDatabase>,
    navController: NavHostController = rememberNavController(),
) {

    val database = remember {
        databaseBuilder
//            .addMigrations(MIGRATIONS)
            .fallbackToDestructiveMigration(true)
            .build()
    }
    val preferences = remember { PreferencesManager() }


    AppTheme(
        isDarkMode = preferences.getTheme().collectAsState(initial = "Light").value == "Dark"
    ) {

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val appScope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ModalNavigationDrawer(
                    scrimColor = MaterialTheme.colorScheme.primary,
                    drawerContent = {
                        AppMenu(
                            navController = navController,
                            preferences = preferences,
                            appScope = appScope,
                            drawerState = drawerState,
                        )
                    },
                    drawerState = drawerState
                ) {
                    val chordFinderViewModel: ChordFinderViewModel = remember { ChordFinderViewModel(database.chordProfileDAO()) }
                            NavGraph(
                                database = database,
                                navController = navController,
                                startDestination = WelcomeScreen,
                                drawerState = drawerState,
                                chordFinderViewModel = chordFinderViewModel
                            )
                }
            }
        }
    }
}


