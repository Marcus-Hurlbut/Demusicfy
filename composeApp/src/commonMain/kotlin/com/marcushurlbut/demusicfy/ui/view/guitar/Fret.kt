package com.marcushurlbut.demusicfy.ui.view.guitar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcushurlbut.demusicfy.ui.viewmodel.GuitarNeckViewModel
import com.marcushurlbut.demusicfy.domain.model.Note

@Composable
fun Fret(note: Note, fret: Int, string: Int, viewModel: GuitarNeckViewModel) {
    val buttonWidth = 42.dp
    val buttonHeight = 64.dp

    Button(
        onClick = {
            viewModel.pressFret(note, string, fret)
        },
        modifier = Modifier
            .padding(4.dp)
            .height(buttonHeight)
            .width(buttonWidth),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = note.getColor()
        ),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = note.getNote(),
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
