package com.marcushurlbut.demusicfy.ui.view.chordfinder.guitar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcushurlbut.demusicfy.domain.model.Note
import com.marcushurlbut.demusicfy.resource.OakWoodImage
import com.marcushurlbut.demusicfy.ui.theme.AppTheme
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel

@Composable
fun Fret(
    note: Note,
    onPressFret: () -> Unit
) {
    Button(
        onClick = {
            onPressFret()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (note.getColor() == AppTheme.fretboard) {Color.Transparent} else {note.getColor()}
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
            overflow = TextOverflow.Ellipsis
        )
    }
}
