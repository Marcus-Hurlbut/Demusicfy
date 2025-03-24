package com.marcushurlbut.demusicfy.ui.view.chordfinder.guitar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.resource.a
import com.marcushurlbut.demusicfy.resource.a_flat
import com.marcushurlbut.demusicfy.resource.b
import com.marcushurlbut.demusicfy.resource.b_flat
import com.marcushurlbut.demusicfy.resource.c
import com.marcushurlbut.demusicfy.resource.d
import com.marcushurlbut.demusicfy.resource.d_flat
import com.marcushurlbut.demusicfy.resource.e
import com.marcushurlbut.demusicfy.resource.e_flat
import com.marcushurlbut.demusicfy.resource.f
import com.marcushurlbut.demusicfy.resource.g
import com.marcushurlbut.demusicfy.resource.g_flat
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel

@Composable
fun GuitarNeck(
    state: ChordFinderUiState,
    viewModel: ChordFinderViewModel
) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(.90f)
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF4E3629))
    ) {
        val notes: List<String> = listOf(
            c(), d_flat(), d(), e_flat(), e(), f(), g_flat(), g(), a_flat(), a(), b_flat(), b()
        )
        var noteIndex = 4
        val intervals = listOf(0, 5, 5, 5, 4, 5)

        for (fret in 0 until 12) {
            Row(
                Modifier
                    .padding(horizontal = 4.dp)
                    .height(48.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (string in 0 until 6) {
                    noteIndex = (noteIndex + intervals[string]) % 12

                    state.notes[string][fret].setNote(notes[noteIndex])
                    state.notes[string][fret].setFretNum(fret)
                    state.notes[string][fret].setStringNum(string)
                    state.notes[string][fret].setIndex(noteIndex)

                    Fret(
                        note = state.notes[string][fret],
                        fret = fret,
                        string = string,
                        viewModel = viewModel
                    )
                }
                noteIndex++
            }
            FretWire()
        }
    }
}