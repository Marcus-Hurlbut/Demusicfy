package com.marcushurlbut.demusicfy.ui.view.chordfinder.profile

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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.domain.data.model.ChordProfile
import com.marcushurlbut.demusicfy.resource.BackArrowIOS
import com.marcushurlbut.demusicfy.resource.DeleteForeverIcon
import com.marcushurlbut.demusicfy.resource.DeleteIcon
import com.marcushurlbut.demusicfy.resource.DisplayOutputIcon
import com.marcushurlbut.demusicfy.resource.EditBoxIcon
import com.marcushurlbut.demusicfy.resource.EditIcon
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppContainer
import com.marcushurlbut.demusicfy.ui.view.util.animation.AnimatedColorBrush
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel

@Composable
fun ChordProfiles(
    state: State<ChordFinderUiState>,
    onListProfiles: () -> Unit,
    onToggleDeleteMode: () -> Unit,
    onToggleEditMode: () -> Unit,
    onPopBackstack: () -> Unit,
    onFormAction: (FormType) -> Unit,
    onOpenForm: (ChordProfile) -> Unit,
    onCloseForm: () -> Unit,
    onSetProfileFromDatabase: (ChordProfile) -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    drawerState: DrawerState,
) {
    val navButtonSize = Modifier.size(28.dp)
    val primaryColor = MaterialTheme.colorScheme.primary


    onListProfiles()

    AppContainer(
        firstButtonIcon = { DeleteIcon(primaryColor, modifier = navButtonSize) },
        onFirstButtonClick = onToggleDeleteMode,
        secondButtonIcon = { EditIcon(primaryColor, modifier = navButtonSize) },
        onSecondButtonClick = onToggleEditMode,
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
                            "Chord Profiles",
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
                        count = state.value.profiles.size,
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
                            Column {
                                Text(
                                    text = state.value.profiles[index].nickname,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                                Text(
                                    text = state.value.profiles[index].chord + " - " + state.value.profiles[index].intervals,
                                    fontSize = 12.sp,
                                    modifier =  Modifier.padding(start = 8.dp)
                                )
                            }

                            Spacer(modifier = Modifier.weight(.35f))
                            if (state.value.editMode) {
                                IconButton(
                                    onClick = {
                                        val formProfile = state.value.profiles[index]
                                        onOpenForm(formProfile)
                                    },
                                    content = { EditBoxIcon(tint = MaterialTheme.colorScheme.onSurface) }
                                )
                            } else if (state.value.deleteMode) {
                                IconButton(
                                    onClick = {
                                        val formProfile = state.value.profiles[index]
                                        onOpenForm(formProfile)
                                    },
                                    content = { DeleteForeverIcon(tint = MaterialTheme.colorScheme.onSurface) }
                                )
                            } else {
                                IconButton(
                                    onClick = {
                                        if (index >= 0) {
                                            onPopBackstack()
                                            onSetProfileFromDatabase(state.value.profiles[index])
                                        }
                                    },
                                    content = { DisplayOutputIcon(tint = MaterialTheme.colorScheme.onSurface) }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(.90f))

            if (state.value.formOpen) {
                ProfileForm(
                    state = state,
                    onFormAction = onFormAction,
                    onCloseForm = onCloseForm,
                    onNicknameValueChanged = onNicknameValueChanged
                )
            }
        }
    }
}