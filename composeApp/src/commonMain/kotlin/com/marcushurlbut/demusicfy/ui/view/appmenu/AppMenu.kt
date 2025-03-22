package com.marcushurlbut.demusicfy.ui.view.appmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.domain.manager.PreferencesManager
import com.marcushurlbut.demusicfy.resource.OpenFolderIcon
import com.marcushurlbut.demusicfy.resource.ThemeIcon
import com.marcushurlbut.demusicfy.ui.view.GuitarNeck
import com.marcushurlbut.demusicfy.ui.view.Metronome
import com.marcushurlbut.demusicfy.ui.view.WelcomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Composable
fun SetUsername(
    preferences: PreferencesManager,
    onClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }

    HorizontalDivider()
    TextField(
        value = username,
        onValueChange = { newText -> username = newText },
        label = { Text("Enter new name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    )
    HorizontalDivider()
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            content = { Text(text = "Save", style = MaterialTheme.typography.labelMedium) },
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    preferences.saveUsername(username)
                }
                onClick()
            },
//            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier
                .fillMaxWidth()

        )
        Button(
            content = { Text(text = "Cancel", style = MaterialTheme.typography.labelMedium) },
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun AppMenu(
    navController: NavHostController,
    preferences: PreferencesManager,
    appScope: CoroutineScope,
    drawerState: DrawerState
) {

    var showThemeOptions by remember { mutableStateOf(false) }
    var showSetUsername by remember { mutableStateOf(false) }

    showThemeOptions = false
    showSetUsername = false

    ModalDrawerSheet {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(12.dp))
            Text(
                preferences.getUsername().collectAsState(initial = "Me").value,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            NavigationDrawerItem(
                label = { Text("Home") },
                selected = false,
                icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                onClick = {
                    navController.navigate(route = WelcomeScreen)
                    appScope.launch {
                        drawerState.close()
                    }
                }
            )

            HorizontalDivider()

            NavigationDrawerItem(
                label = { Text("Chord Finder") },
                selected = false,
                icon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null) },
                onClick = {
                    navController.navigate(route = GuitarNeck)
                    appScope.launch {
                        drawerState.close()
                    }
                }
            )
            NavigationDrawerItem(
                label = { Text("Metronome") },
                selected = false,
                icon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null) },
                onClick = {
                    navController.navigate(route = Metronome)
                    appScope.launch {
                        drawerState.close()
                    }
                }
            )

            HorizontalDivider()

            Text(
                "My Account",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            NavigationDrawerItem(
                label = { Text("Username") },
                selected = false,
                icon = { Icon(Icons.Outlined.AccountBox, contentDescription = null) },
                onClick = { showSetUsername = !showSetUsername }
            )
            if (showSetUsername) {
                SetUsername(
                    preferences = preferences,
                    onClick = { showSetUsername = false }
                )
            }
            NavigationDrawerItem(
                label = { Text("Library") },
                selected = false,
                icon = { OpenFolderIcon(MaterialTheme.colorScheme.onBackground) },
                onClick = { /* Handle click */ }
            )
            NavigationDrawerItem(
                label = { Text("Reset Data") },
                selected = false,
                icon = { Icon(Icons.Outlined.Refresh, contentDescription = null) },
                onClick = { /* Handle click */ }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                "App",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            NavigationDrawerItem(
                label = { Text("Settings") },
                selected = false,
                icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                onClick = { /* Handle click */ }
            )
            NavigationDrawerItem(
                label = { Text("Theme") },
                selected = false,
                icon = {
                    ThemeIcon(
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                },
                badge = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                },
                onClick = {
                    showThemeOptions = !showThemeOptions
                },
            )
            if (showThemeOptions) {
                val options = listOf("Light", "Dark", "System")

                SingleChoiceSegmentedButtonRow {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = options.size
                            ),
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    preferences.saveTheme(
                                        options[index]
                                    )
                                }
                            },
                            selected = options[index] == preferences.getTheme().collectAsState(initial = "Light").value,
                            label = { Text(label) }
                        )
                    }
                }
            }
            NavigationDrawerItem(
                label = { Text("Help and feedback") },
                selected = false,
                icon = { Icon(Icons.AutoMirrored.Outlined.Send, contentDescription = null) },
                onClick = { /* Handle click */ }
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}