package com.marcushurlbut.demusicfy.ui.view.metronome

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.domain.data.dao.MetronomeProfileDAO
import com.marcushurlbut.demusicfy.domain.data.model.MetronomeProfile
import com.marcushurlbut.demusicfy.resource.BackArrowIOS
import com.marcushurlbut.demusicfy.resource.DeleteForeverIcon
import com.marcushurlbut.demusicfy.resource.EditBoxIcon
import com.marcushurlbut.demusicfy.ui.view.metronome.components.FormType
import com.marcushurlbut.demusicfy.ui.view.metronome.components.ProfileForm
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeProfilesViewModel

@Composable
fun MetronomeProfiles(
    navController: NavHostController,
    viewModel: MetronomeProfilesViewModel
) {
//    val viewModel: MetronomeProfilesViewModel = remember { MetronomeProfilesViewModel(dao) }
    val uiState by viewModel.uiState.collectAsState()
    var formType by remember { mutableStateOf(FormType.CREATE) }

    viewModel.listProfiles()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Box(Modifier.fillMaxWidth().fillMaxHeight(.08f)) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    content = { BackArrowIOS(MaterialTheme.colorScheme.onPrimaryContainer) },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 0.dp, y =  5.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text("Metronome Profiles",
                        fontSize = 24.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }

        val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
        val animatedColor by infiniteTransition.animateColor(
            initialValue = Color.Magenta,
            targetValue = MaterialTheme.colorScheme.tertiary,
            animationSpec = infiniteRepeatable(tween(4000), RepeatMode.Reverse),
            label = "color"
        )

        val gradientBrush = Brush.horizontalGradient(
            colors = listOf(animatedColor, animatedColor),
            startX = 0.0f,
            endX = 500.0f,
            tileMode = TileMode.Clamp
        )
        Spacer(modifier = Modifier.weight(.10f))
        ElevatedCard {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .fillMaxHeight(.70f)
                    .border(
                        width = 4.dp,
                        brush = gradientBrush,
                        shape = RectangleShape
                    )
            ) {
                items(
                    count = uiState.profiles.size,
                ) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
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

        formType = when {
            uiState.editMode -> {FormType.EDIT}
            uiState.deleteMode -> {FormType.DELETE}
            else -> {FormType.CREATE}
        }

        if (uiState.formOpen) {
            ProfileForm(
                formType = formType,
                viewModel = viewModel,
            )
        }
    }
}