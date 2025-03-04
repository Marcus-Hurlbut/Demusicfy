package com.marcushurlbut.demusicfy.domain

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Fret(fret: Int, _note: String, noteIndex: Int, onStringClick: (Int, Int) -> Unit) {
    val buttonWidth = 50.dp
    val buttonHeight = 65.dp

    Row(
        Modifier
            .fillMaxWidth()
            .height(buttonHeight),
        horizontalArrangement = Arrangement.Center
    ) {
        val notes : List<String> = listOf("C", "D♭", "D", "E♭", "E", "F", "G♭", "G", "A♭", "A", "B♭", "B")
        val intervals = listOf(0, 5, 5, 5, 4, 5)
        var index = noteIndex
        var note = _note


        for (stringIndex in 0 until 6) {
            val isPressed = false
            index = (index + intervals[stringIndex]) % 12
            note = notes[index]

            Button(
                onClick = { onStringClick(stringIndex, fret) },
                modifier = Modifier
                    .padding(5.dp)
                    .height(buttonHeight)
                    .width(buttonWidth),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isPressed) Color.Green else Color(0xFF9C7B5E)
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = note,
                    color = Color.White,
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFeatureSettings = "",
                        fontFamily = FontFamily.Monospace,

                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}
