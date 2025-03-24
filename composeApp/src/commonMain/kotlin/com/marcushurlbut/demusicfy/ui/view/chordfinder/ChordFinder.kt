import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.marcushurlbut.demusicfy.ui.view.chordfinder.ChordFinderDisplay
import com.marcushurlbut.demusicfy.ui.view.chordfinder.guitar.GuitarNeck
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel

@Composable
fun ChordFinder(
    viewModel: ChordFinderViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChordFinderDisplay(state = state)
        Spacer(modifier = Modifier.weight(1f))
        GuitarNeck(
            state = state,
            viewModel = viewModel
        )
    }
}
