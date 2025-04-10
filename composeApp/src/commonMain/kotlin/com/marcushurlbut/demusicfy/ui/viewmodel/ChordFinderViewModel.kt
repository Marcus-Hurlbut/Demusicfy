package com.marcushurlbut.demusicfy.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcushurlbut.demusicfy.domain.data.dao.ChordProfileDAO
import com.marcushurlbut.demusicfy.domain.data.model.ChordProfile
import com.marcushurlbut.demusicfy.domain.interpreter.ChordInterpreter
import com.marcushurlbut.demusicfy.domain.interpreter.ChordType
import com.marcushurlbut.demusicfy.domain.model.Note
import com.marcushurlbut.demusicfy.ui.theme.AppTheme
import com.marcushurlbut.demusicfy.ui.view.chordfinder.profile.FormType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ChordFinderUiState(
    val root: Note? = null,
    val extensions: List<Note> = emptyList(),
    val notes: Array<Array<Note>> = Array(6) { Array(12) { Note() } },
    val chord: String = "Unknown",
    val intervals: String = "",

    val profile: ChordProfile = ChordProfile(chord = "", intervals = ""),
    val profiles: List<ChordProfile> = listOf(),

    val formOpen: Boolean = false,
    val editMode: Boolean = false,
    val deleteMode: Boolean = false,
    val formType: FormType = FormType.CREATE
)

class ChordFinderViewModel(
    private val dao: ChordProfileDAO
) : ViewModel() {
    private val _state = MutableStateFlow(ChordFinderUiState())
    val state: StateFlow<ChordFinderUiState> = _state
    val chordInterpreter = ChordInterpreter()

    fun pressFret(note: Note, stringNum: Int, fretNum: Int) {
        viewModelScope.launch {
            val currentState = _state.value
            val currentNote = currentState.notes[stringNum][fretNum]
            val isPressed = currentNote.isPressed()

            // Root note clicked again, remove it
            if (currentState.root != null && isSameLocation(currentState.root, note)) {
                updateCurrentProfile(currentState.root.getStringNum(), null)

                _state.value = currentState.copy(
                    root = null,
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.fretboard)
                    }
                )
            }
            else if (currentState.root != null && isSameString(currentState.root, note)) {
                val prevRoot = currentState.root

                _state.value = _state.value.copy(
                    root = note,
                    profile = currentState.profile.apply {
                        rootFret = fretNum
                        rootString = stringNum
                    },
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.darkPalette.primary)
                        this[prevRoot.getLocation().first][prevRoot.getLocation().second].setColor(AppTheme.fretboard)
                    }
                )
            }
            // If no root is set, set the clicked note as the root
            else if (currentState.root == null) {
                _state.value = currentState.copy(
                    root = note,
                    profile = currentState.profile.apply {
                        rootFret = fretNum
                        rootString = stringNum
                    },
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.darkPalette.primary)
                    }
                )
                updateCurrentProfile(stringNum, fretNum)
            }
            // Remove extension note
            else if (currentState.extensions.contains(note)) {
                _state.value = currentState.copy(
                    extensions = currentState.extensions.filter { it != note },
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.fretboard)
                    }
                )
                updateCurrentProfile(stringNum, null)
            }
            // Add extension note
            else {
                _state.value = currentState.copy(
                    extensions = currentState.extensions + note,
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.darkPalette.primaryContainer)
                    }
                )
                updateCurrentProfile(stringNum, fretNum)
            }

            currentNote.setPressed(!isPressed)
            setChordTextDisplay(_state.value.root, _state.value.extensions)
            setIntervalTextDisplay(_state.value.root, _state.value.extensions)
        }
    }

    fun clearNotes() {
        viewModelScope.launch {
            val currentState = _state.value

            _state.value = currentState.copy(
                root = null,
                extensions = emptyList(),
                notes = currentState.notes.apply {
                    for (i in 0 until 12) {
                        for (j in 0 until 6) {
                            this[j][i].setColor(Color(0xFF4E3629))
                        }
                    }
                },
                profile = ChordProfile(chord = "", intervals = ""),
                intervals = "",
                chord = ""
            )
        }
    }

    private fun setIntervalTextDisplay(note: Note?, extensions: List<Note>) {
        viewModelScope.launch {
            if (note == null) {
                return@launch
            }
            val currentState = _state.value
            val intervalVals = chordInterpreter.interval(note, extensions)
            var intervalString = ""

            for (interval in intervalVals.sorted()) {
                val charText = when (interval) {
                    0 -> "R"
                    1 -> "♭2"
                    2 -> "2"
                    3 -> "♭3"
                    4 -> "3"
                    5 -> "4"
                    6 -> "♭5"
                    7 -> "5"
                    8 -> "♭6"
                    9 -> "6"
                    10 -> "♭7"
                    11 -> "7"
                    else -> ""
                }
                intervalString += charText
                intervalString += " "
            }

            _state.value = currentState.copy(
                intervals = intervalString,
                profile = currentState.profile.apply {
                    intervals = intervalString
                }
            )
        }
    }

    private fun setChordTextDisplay(note: Note?, extensions: List<Note>) {
        viewModelScope.launch {
            if (note == null) {
                return@launch
            }

            val chordType = chordInterpreter.chord(note, extensions)
            val chordTypeText = when (chordType) {
                ChordType.Undefined -> "None"
                ChordType.Major -> "maj"
                ChordType.Minor -> "m"
                ChordType.PowerChord, -> "5"
                ChordType.Suspended -> "sus"
                ChordType.Augmented -> "aug"
                ChordType.FullDiminished,
                ChordType.HalfDiminished -> "min7♭5"

                ChordType.DominantSeventh -> "7"
                ChordType.MajorSeventh -> "maj7"
                ChordType.MinorSeventh -> "min7"
                ChordType.MinorNinth -> "min9"
                ChordType.MajorNinth -> "maj9"
                ChordType.MajorSixth -> "maj6"
                ChordType.MinorSixth -> "min6"
                ChordType.MajorEleventh -> "maj11"
                ChordType.MinorEleventh -> "min11"
                ChordType.MajorAddNine -> "maj"
                ChordType.MinorAddNine -> TODO()
            }

            val chordText = if (chordType != ChordType.Undefined) {
                note.getNote() + " " + chordTypeText
            } else {
                chordTypeText
            }

            val currentState = _state.value
            _state.value = currentState.copy(
                chord = chordText,
                profile = currentState.profile.apply {
                    chord = chordText
                }
            )
        }
    }

    private fun isSameLocation(note1: Note?, note2: Note): Boolean {
        return note1?.getStringNum() == note2.getStringNum() && note1.getFretNum() == note2.getFretNum()
    }

    private fun isSameString(note1: Note?, note2: Note): Boolean {
        return note1?.getStringNum() == note2.getStringNum()
    }

    fun setProfileFromDatabase(profile: ChordProfile) {
        viewModelScope.launch {
            val currentState = state.value
            val profileExtensions = mutableListOf<Note>()

            if (profile.string1 != null) {
                profileExtensions += currentState.notes[0][profile.string1!!]
            }
            if (profile.string2 != null) {
                profileExtensions += currentState.notes[1][profile.string2!!]
            }
            if (profile.string3 != null) {
                profileExtensions += currentState.notes[2][profile.string3!!]
            }
            if (profile.string4 != null) {
                profileExtensions += currentState.notes[3][profile.string4!!]
            }
            if (profile.string5 != null) {
                profileExtensions += currentState.notes[4][profile.string5!!]
            }
            if (profile.string6 != null) {
                profileExtensions += currentState.notes[5][profile.string6!!]
            }
            profileExtensions.remove(currentState.notes[profile.rootString][profile.rootFret])

            _state.value = currentState.copy(
                notes = currentState.notes.apply {
                    if (profile.string1 != null) {
                        this[0][profile.string1!!].setColor(Color.Green)
                    }
                    if (profile.string2 != null) {
                        this[1][profile.string2!!].setColor(AppTheme.darkPalette.primaryContainer)
                    }
                    if (profile.string3 != null) {
                        this[2][profile.string3!!].setColor(AppTheme.darkPalette.primaryContainer)
                    }
                    if (profile.string4 != null) {
                        this[3][profile.string4!!].setColor(AppTheme.darkPalette.primaryContainer)
                    }
                    if (profile.string5 != null) {
                        this[4][profile.string5!!].setColor(AppTheme.darkPalette.primaryContainer)
                    }
                    if (profile.string6 != null) {
                        this[5][profile.string6!!].setColor(AppTheme.darkPalette.primaryContainer)
                    }
                    this[profile.rootString][profile.rootFret].setColor(AppTheme.darkPalette.primary)

                },
                root = currentState.notes[profile.rootString][profile.rootFret],
                intervals = profile.intervals,
                chord = profile.chord,
                extensions = profileExtensions,
                profile = profile
            )
        }
    }

    private fun updateCurrentProfile(string: Int, fret: Int?) {
        val currentState = _state.value

        when (string) {
            0 -> { _state.value = currentState.copy(profile = currentState.profile.apply { string1 = fret })}
            1 -> { _state.value = currentState.copy(profile = currentState.profile.apply { string2 = fret })}
            2 -> { _state.value = currentState.copy(profile = currentState.profile.apply { string3 = fret })}
            3 -> { _state.value = currentState.copy(profile = currentState.profile.apply { string4 = fret })}
            4 -> { _state.value = currentState.copy(profile = currentState.profile.apply { string5 = fret })}
            5 -> { _state.value = currentState.copy(profile = currentState.profile.apply { string6 = fret })}
        }
    }

    fun formAction(formType: FormType) {
        val profile = state.value.profile
        viewModelScope.launch {
            when (formType) {
                FormType.CREATE -> saveChord(profile)
                FormType.EDIT -> editNickname(profile)
                FormType.DELETE -> deleteChord(profile)
            }
        }
    }

    fun closeForm() {
        viewModelScope.launch {
            val currentState = state.value

            _state.value = currentState.copy(
                formOpen = false
            )
        }
    }

    fun openForm(profile: ChordProfile) {
        viewModelScope.launch {
            val currentState = _state.value
            _state.value = currentState.copy(
                formOpen = true,
                profile = profile
            )
        }
    }

    fun toggleEditMode() {
        viewModelScope.launch {
            val currentState = _state.value
            _state.value = currentState.copy(
                deleteMode = false,
                editMode = !currentState.editMode
            )
        }
    }

    fun toggleDeleteMode() {
        viewModelScope.launch {
            val currentState = _state.value
            _state.value = currentState.copy(
                editMode = false,
                deleteMode = !currentState.deleteMode
            )
        }
    }

    fun onNicknameValueChanged(newText: String) {
        if (newText != "") {
            viewModelScope.launch {
                val currentState = _state.value
                val updatedProfile = currentState.profile.copy(nickname = newText)
                _state.value = currentState.copy(profile = updatedProfile)
            }
        }
    }

    fun saveChord(chordProfile: ChordProfile) {
        println("Saving chord: $chordProfile")
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(chordProfile)
        }
    }

    fun editNickname(chordProfile: ChordProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(chordProfile)
        }
    }

    fun deleteChord(chordProfile: ChordProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(chordProfile)
        }
    }

    fun listProfiles() {
        viewModelScope.launch(Dispatchers.IO)  {
            val currentState = _state.value

            _state.value = currentState.copy(
                profiles = dao.listAll()
            )
        }
    }




}
