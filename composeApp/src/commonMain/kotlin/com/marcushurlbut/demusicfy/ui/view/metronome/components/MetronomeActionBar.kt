package com.marcushurlbut.demusicfy.ui.view.metronome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.marcushurlbut.demusicfy.resource.EQIcon
import com.marcushurlbut.demusicfy.resource.OpenFolderIcon
import com.marcushurlbut.demusicfy.resource.PauseIcon
import com.marcushurlbut.demusicfy.resource.PlayIcon
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.MetronomeViewModel
import com.marcushurlbut.demusicfy.ui.view.MetronomeProfiles

@Composable
fun MetronomeActionBar(
    navController: NavHostController,
    viewModel: MetronomeViewModel,
) {
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(40.dp))
//            .background(color = MaterialTheme.colorScheme.primary),
//        contentAlignment = Alignment.BottomEnd
//
//    ) {
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .clip(RoundedCornerShape(40.dp)),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.Bottom
//        ) {
//            IconButton(
//                onClick = { viewModel.stop() },
//                modifier = Modifier,
//                content = { PauseIcon() }
//            )
//            IconButton(
//                onClick = { viewModel.play() },
//                modifier = Modifier,
//                content = {
//                    PlayIcon(
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        modifier = Modifier.size(48.dp)
//                    )
//                }
//            )
//            IconButton(
//                onClick = { },
//                modifier = Modifier,
//                content = {
//                    EQIcon(
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        modifier = Modifier.size(48.dp)
//                    )
//                }
//            )
//            IconButton(
//                onClick = {
//                    navController.navigate(route = MetronomeProfiles)
//                },
//                modifier = Modifier,
//                content = {
//                    OpenFolderIcon(
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        modifier = Modifier.size(48.dp)
//                    )
//                }
//            )
//        }
//    }
}