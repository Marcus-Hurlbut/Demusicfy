package com.marcushurlbut.demusicfy.ui.view.chordfinder.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.marcushurlbut.demusicfy.resource.CheckIcon
import com.marcushurlbut.demusicfy.resource.CloseIcon
import com.marcushurlbut.demusicfy.resource.CursiveFontFamily
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel

enum class FormType {
    CREATE,
    EDIT,
    DELETE
}

@Composable
fun ProfileForm(
//    viewModel: ChordFinderViewModel,
    onFormAction: (FormType) -> Unit,
    onCloseForm: () -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    state: State<ChordFinderUiState>,
) {
    val formType = when {
        state.value.editMode -> { FormType.EDIT }
        state.value.deleteMode -> { FormType.DELETE }
        else -> { FormType.CREATE }
    }

    if (state.value.formOpen) {
        Dialog(
            onDismissRequest = onCloseForm
        ) {
            Card() {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "${formType.name.lowercase().capitalize()} Chord Profile",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = CursiveFontFamily(),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    HorizontalDivider()
                    Row(modifier = Modifier.padding(8.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Chord", fontSize = 20.sp)
                            Text(state.value.profile.chord, fontSize = 16.sp)
                        }
                    }
                    Row(modifier = Modifier.padding(8.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Intervals", fontSize = 20.sp)
                            Text(state.value.profile.intervals, fontSize = 16.sp)
                        }
                    }
                    HorizontalDivider()
                    Row(Modifier.padding(8.dp).padding(top = 16.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Nickname", fontSize = 20.sp)
                            TextField(
                                value = state.value.profile.nickname,
                                onValueChange = { newText: String ->
                                    onNicknameValueChanged(newText)
                                },
                                readOnly = formType == FormType.DELETE
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = onCloseForm,
                            content = {
                                CloseIcon(
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        )
                        IconButton(
                            onClick = {
                                onFormAction(formType)
                                onCloseForm()
                            },
                            content = {
                                CheckIcon(
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}