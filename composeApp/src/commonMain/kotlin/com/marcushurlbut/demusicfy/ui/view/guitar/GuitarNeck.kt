import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.resource.ClearIcon
import com.marcushurlbut.demusicfy.resource.OpenFolderIcon
import com.marcushurlbut.demusicfy.resource.SaveIcon
import com.marcushurlbut.demusicfy.ui.view.guitar.Fret
import com.marcushurlbut.demusicfy.ui.view.guitar.FretWire
import com.marcushurlbut.demusicfy.ui.viewmodel.GuitarNeckViewModel
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
import com.marcushurlbut.demusicfy.ui.view.guitar.ChordFinderDisplay

@Composable
fun GuitarNeck(
    viewModel: GuitarNeckViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChordFinderDisplay(uiState = uiState)

        Spacer(modifier = Modifier.weight(1f))

        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(.85f)
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
                        .padding(horizontal = 10.dp)
                        .height(48.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (string in 0 until 6) {
                        noteIndex = (noteIndex + intervals[string]) % 12

                        uiState.notes[string][fret].setNote(notes[noteIndex])
                        uiState.notes[string][fret].setFretNum(fret)
                        uiState.notes[string][fret].setStringNum(string)
                        uiState.notes[string][fret].setIndex(noteIndex)

                        Fret(
                            note = uiState.notes[string][fret],
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
}
