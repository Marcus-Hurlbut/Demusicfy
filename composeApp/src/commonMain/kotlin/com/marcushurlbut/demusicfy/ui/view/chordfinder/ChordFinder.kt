import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcushurlbut.demusicfy.domain.data.model.ChordProfile
import com.marcushurlbut.demusicfy.domain.model.Note
import com.marcushurlbut.demusicfy.resource.ClearIcon
import com.marcushurlbut.demusicfy.resource.OpenFolderIcon
import com.marcushurlbut.demusicfy.resource.SaveIcon
import com.marcushurlbut.demusicfy.ui.view.appmenu.AppContainer
import com.marcushurlbut.demusicfy.ui.view.chordfinder.ChordFinderDisplay
import com.marcushurlbut.demusicfy.ui.view.chordfinder.guitar.GuitarNeck
import com.marcushurlbut.demusicfy.ui.view.chordfinder.profile.FormType
import com.marcushurlbut.demusicfy.ui.view.chordfinder.profile.ProfileForm
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderUiState
import com.marcushurlbut.demusicfy.ui.viewmodel.ChordFinderViewModel

@Composable
fun ChordFinder(
    state: State<ChordFinderUiState>,
    onClearNotes: () -> Unit,
    onPressFret: (Note, Int, Int) -> Unit,
    onFormAction: (FormType) -> Unit,
    onOpenForm: (ChordProfile) -> Unit,
    onCloseForm: () -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    onNavigateToChordProfiles: () -> Unit,
    drawerState: DrawerState
) {
    val color = MaterialTheme.colorScheme.primary
    val navButtonSize = Modifier.size(28.dp)

    AppContainer(
        drawerState = drawerState,
        onFirstButtonClick = onClearNotes,
        onSecondButtonClick = {
            onOpenForm(
                ChordProfile(
                    chord = state.value.chord,
                    intervals = state.value.intervals,
                    nickname = state.value.chord,
                    string1 = state.value.profile.string1,
                    string2 = state.value.profile.string2,
                    string3 = state.value.profile.string3,
                    string4 = state.value.profile.string4,
                    string5 = state.value.profile.string5,
                    string6 = state.value.profile.string6,
                    rootString = state.value.profile.rootString,
                    rootFret = state.value.profile.rootFret
                )
            )
        },
        onThirdButtonClick = onNavigateToChordProfiles,
        firstButtonIcon = { ClearIcon(color, modifier = navButtonSize) },
        secondButtonIcon = { SaveIcon(color, modifier = navButtonSize) },
        thirdButtonIcon = { OpenFolderIcon(color, modifier = navButtonSize) },
    ) {
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
                onPressFret = onPressFret
            )

            ProfileForm(
                state = state,
                onFormAction = onFormAction,
                onCloseForm = onCloseForm,
                onNicknameValueChanged = onNicknameValueChanged
            )
        }
    }
}
