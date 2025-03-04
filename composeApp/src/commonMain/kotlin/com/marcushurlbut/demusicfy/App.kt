package com.marcushurlbut.demusicfy

import GuitarNeck
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.resource.getAppIconResource

@Composable
fun App() {
    MaterialTheme(
        colors = MaterialTheme.colors
    ) {
        var showContent by remember { mutableStateOf(false) }
        var rootNote by remember { mutableStateOf<String?>(null) }
        var chordExtensions by remember { mutableStateOf<List<Int>>(emptyList()) }

        // Main Column Layout
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Demusicfy",
                    style = MaterialTheme.typography.h3.copy(color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold),
                    modifier = Modifier,
                )
                Image(
                    painter = getAppIconResource(),
                    contentDescription = "Guitar Logo",
                    modifier = Modifier.size(75.dp)
                )
            }
            Text(
                text = "Learn complex chords from anywhere!",
                style = MaterialTheme.typography.h6.copy(color = Color.Gray),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Button(
                onClick = { showContent = !showContent },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
            ) {
                Text(
                    text = if (showContent) "Close Guitar" else "Open Guitar",
                    style = MaterialTheme.typography.button.copy(color = Color.White)
                )
            }

            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Select the root note", style = MaterialTheme.typography.subtitle1)
                    GuitarNeck(
                        onStringClick = { stringIndex, fret ->
                            if (rootNote == null) {
                                // rootNote = guitarStrings[stringIndex]
                            } else {
                                chordExtensions = chordExtensions + fret
                            }
                        },
                        rootNote = rootNote
                    )
                }
            }
        }
    }
}
