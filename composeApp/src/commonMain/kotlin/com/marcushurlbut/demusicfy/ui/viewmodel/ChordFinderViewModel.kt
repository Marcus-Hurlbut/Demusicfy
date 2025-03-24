package com.marcushurlbut.demusicfy.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcushurlbut.demusicfy.domain.interpreter.ChordInterpreter
import com.marcushurlbut.demusicfy.domain.interpreter.ChordType
import com.marcushurlbut.demusicfy.domain.model.Note
import com.marcushurlbut.demusicfy.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Define a data class to represent the state of the UI
data class ChordFinderUiState(
    val root: Note? = null,
    val extensions: List<Note> = emptyList(),
    val notes: Array<Array<Note>> = Array(6) { Array(12) { Note() } },
    var chord: String = "None",
    var intervals: String = ""
)

class ChordFinderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChordFinderUiState())
    val uiState: StateFlow<ChordFinderUiState> = _uiState
    val chordInterpreter = ChordInterpreter()

    fun pressFret(note: Note, stringNum: Int, fretNum: Int) {
        viewModelScope.launch {
            val currentState = _uiState.value
            val currentNote = currentState.notes[stringNum][fretNum]
            val isPressed = currentNote.isPressed()

            // Root note clicked again, remove it
            if (currentState.root != null && isSameLocation(currentState.root, note)) {
                _uiState.value = currentState.copy(
                    root = null,
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.fretboard)
                    }
                )
            }
            // If no root is set, set the clicked note as the root (turn it blue)
            else if (currentState.root == null) {
                _uiState.value = currentState.copy(
                    root = note,
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.darkPalette.primary)
                    }
                )
            }
            // Remove extension note
            else if (currentState.extensions.contains(note)) {
                _uiState.value = currentState.copy(
                    extensions = currentState.extensions.filter { it != note },
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.fretboard)
                    }
                )
            }
            // Add extension note
            else {
                _uiState.value = currentState.copy(
                    extensions = currentState.extensions + note,
                    notes = currentState.notes.apply {
                        this[stringNum][fretNum].setColor(AppTheme.darkPalette.tertiary)
                    }
                )
            }

            currentNote.setPressed(!isPressed)
            setChordTextDisplay(_uiState.value.root, _uiState.value.extensions)
            setIntervalTextDisplay(_uiState.value.root, _uiState.value.extensions)
        }
    }

    private fun setIntervalTextDisplay(note: Note?, extensions: List<Note>) {
        viewModelScope.launch {
            if (note == null) {
                return@launch
            }
            val currentState = _uiState.value
            val intervals = chordInterpreter.interval(note, extensions)
            var intervalString = ""

            for (interval in intervals.sorted()) {
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

            _uiState.value = currentState.copy(
                intervals = intervalString
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

            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                chord = chordText
            )
        }
    }

    fun clearNotes() {
        viewModelScope.launch {
            val currentState = _uiState.value

            _uiState.value = currentState.copy(
                root = null,
                extensions = emptyList(),
                notes = currentState.notes.apply {
                    for (i in 0 until 12) {
                        for (j in 0 until 6) {
                            this[j][i].setColor(Color(0xFF4E3629))
                        }
                    }
                },
                chord = ""
            )
        }
    }

    private fun isSameLocation(note1: Note?, note2: Note): Boolean {
        return note1?.getStringNum() == note2.getStringNum() && note1.getFretNum() == note2.getFretNum()
    }
}
