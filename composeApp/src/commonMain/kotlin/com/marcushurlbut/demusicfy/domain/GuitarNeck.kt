import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.domain.Fret
import com.marcushurlbut.demusicfy.domain.FretWire
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GuitarNeck(onStringClick: (Int, Int) -> Unit, rootNote: String?) {
    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF4E3629))
    ) {
        val notes : List<String> = listOf("C", "D♭", "D", "E♭", "E", "F", "G♭", "G", "A♭", "A", "B♭", "B")
        var noteNum = 4
        for (fret in 0 until 12) {

            Fret(fret = fret, _note = notes[noteNum], noteIndex = noteNum, onStringClick = onStringClick)
            FretWire()

            noteNum = (noteNum + 1) % 12
        }
    }
}
@Preview
@Composable
fun GuitarNeckPreview() {
    GuitarNeck(onStringClick = { _, _ -> }, rootNote = "C")
}
