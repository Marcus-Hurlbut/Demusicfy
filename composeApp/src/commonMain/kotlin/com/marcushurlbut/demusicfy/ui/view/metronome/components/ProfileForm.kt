package com.marcushurlbut.demusicfy.ui.view.metronome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.marcushurlbut.demusicfy.resource.BerkshireFontFamily
import com.marcushurlbut.demusicfy.resource.CheckIcon
import com.marcushurlbut.demusicfy.resource.CloseIcon
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeProfilesViewModel

enum class FormType {
    CREATE,
    EDIT,
    DELETE
}



@Composable
fun ProfileForm(
    formType: FormType,
    viewModel: MetronomeProfilesViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    Dialog(
        onDismissRequest = { viewModel.closeForm() }
    ) {
        Card() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "${formType.name.lowercase().capitalize()} Metronome Profile",
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = BerkshireFontFamily(),
                        fontSize = 24.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                HorizontalDivider()
                Row(Modifier.padding(8.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Song", fontSize = 20.sp)
                        TextField(
                            value = uiState.openProfile.song,
                            onValueChange = { newText : String -> viewModel.onSongValueChanged(newText) },
                            readOnly = formType == FormType.DELETE
                        )
                    }
                }
                Row(Modifier.padding(8.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Artist", fontSize = 20.sp)
                        TextField(
                            value = uiState.openProfile.artist,
                            onValueChange = { newText : String -> viewModel.onArtistValueChanged(newText) },
                            readOnly = formType == FormType.DELETE
                        )
                    }
                }
                Row(Modifier.padding(8.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("BPM", fontSize = 20.sp)
                        TextField(
                            value = uiState.openProfile.bpm.toString(),
                            onValueChange = { newText : String -> viewModel.onBPMValueChanged(newText)},
                            readOnly = formType == FormType.DELETE
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            viewModel.closeForm()
                        },
                        content = {
                            CloseIcon(
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    )
                    IconButton(
                        onClick = {
                            viewModel.formAction(formType)
                            viewModel.closeForm()
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