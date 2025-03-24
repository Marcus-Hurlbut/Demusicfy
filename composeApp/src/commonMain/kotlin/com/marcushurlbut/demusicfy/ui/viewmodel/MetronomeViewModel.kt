package com.marcushurlbut.demusicfy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcushurlbut.demusicfy.domain.data.dao.MetronomeProfileDAO
import com.marcushurlbut.demusicfy.domain.data.model.MetronomeProfile
import com.marcushurlbut.demusicfy.domain.manager.SoundManager
import com.marcushurlbut.demusicfy.ui.view.metronome.Metronome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MetronomeUiState(
    val bpm: Int = 128,
    val playing: Boolean = false,
    val soundManager: SoundManager = SoundManager(),
    val currentSound: String = ""
)

class MetronomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MetronomeUiState())
    val uiState : StateFlow<MetronomeUiState> = _uiState

    fun play() {
        // Exoplayer must be on a single thread - documentation states
        // its best to use main thread for use
        viewModelScope.launch(Dispatchers.Main) {
            uiState.value.soundManager.play(_uiState.value.bpm)
        }
    }

    fun stop() {
        viewModelScope.launch(Dispatchers.Main) {
            uiState.value.soundManager.stop()
        }
    }

    fun incrementBPM() {
        adjustTiming(_uiState.value.bpm + 1)
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                bpm = currentState.bpm + 1
            )
        }
    }
    fun decrementBPM() {
        adjustTiming(_uiState.value.bpm - 1)
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                bpm = currentState.bpm - 1
            )
        }
    }

    fun setBPM(newBPM : Int) {
        adjustTiming(newBPM)
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                bpm = newBPM
            )
        }
    }

    fun switchSound(sound: String) {
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value.soundManager.switchSound(sound)

            _uiState.value = currentState.copy(
                currentSound = _uiState.value.soundManager.sound
            )
        }
    }

    private fun adjustTiming(bpm: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            uiState.value.soundManager.adjustTiming(bpm)
        }
    }
}