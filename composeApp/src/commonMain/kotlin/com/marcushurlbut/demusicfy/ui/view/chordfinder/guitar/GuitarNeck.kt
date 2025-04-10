package com.marcushurlbut.demusicfy.ui.view.chordfinder.guitar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.domain.model.Note
import com.marcushurlbut.demusicfy.resource.OakWoodImage
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
import kotlin.reflect.KFunction3

@Composable
fun GuitarNeck(
    state: State<ChordFinderUiState>,
    onPressFret: (Note, Int, Int) -> Unit
) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(.85f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF4E3629))
    ) {
        val notes: List<String> = listOf(
            c(), d_flat(), d(), e_flat(), e(), f(), g_flat(), g(), a_flat(), a(), b_flat(), b()
        )
        var noteIndex = 4
        val intervals = listOf(0, 5, 5, 5, 4, 5)

        for (fret in 0 until 12) {
            Row(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {
                for (string in 0 until 6) {
                    noteIndex = (noteIndex + intervals[string]) % 12

                    state.value.notes[string][fret].setNote(notes[noteIndex])
                    state.value.notes[string][fret].setFretNum(fret)
                    state.value.notes[string][fret].setStringNum(string)
                    state.value.notes[string][fret].setIndex(noteIndex)

                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .paint((OakWoodImage()))
                    ) {
                        Fret(
                            note = state.value.notes[string][fret],
                            onPressFret = { onPressFret(state.value.notes[string][fret], string, fret) }
//                            onPressFret = viewModel.pressFret(state.value.notes[string][fret], string, fret)
                        )
                    }
                }
                noteIndex++
            }
            FretWire()
        }
    }
}