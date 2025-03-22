package com.marcushurlbut.demusicfy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcushurlbut.demusicfy.domain.data.dao.MetronomeProfileDAO
import com.marcushurlbut.demusicfy.domain.data.model.MetronomeProfile
import com.marcushurlbut.demusicfy.ui.view.metronome.components.FormType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MetronomeProfilesUiState(
    val profiles: List<MetronomeProfile> = listOf(),
    val formOpen: Boolean = false,
    val editMode: Boolean = false,
    val deleteMode: Boolean = false,
    val openProfile: MetronomeProfile = MetronomeProfile(song = "", artist = "", bpm = 0)
)

class MetronomeProfilesViewModel(
    private val dao: MetronomeProfileDAO
) : ViewModel() {
    private val _uiState = MutableStateFlow(MetronomeProfilesUiState())
    val uiState : StateFlow<MetronomeProfilesUiState> = _uiState

    fun onSongValueChanged(newText: String) {
        viewModelScope.launch {
            val currentState = _uiState.value
            val updatedProfile = currentState.openProfile.copy(song = newText)
            _uiState.value = currentState.copy(openProfile = updatedProfile)
        }
    }

    fun onArtistValueChanged(newText: String) {
        viewModelScope.launch {
            val currentState = _uiState.value
            val updatedProfile = currentState.openProfile.copy(artist = newText)
            _uiState.value = currentState.copy(openProfile = updatedProfile)
        }
    }

    fun onBPMValueChanged(newText: String) {
        // TODO - input validation for int type
        if (newText != "") {
            viewModelScope.launch {
                val currentState = _uiState.value
                val updatedProfile = currentState.openProfile.copy(bpm = newText.toInt())
                _uiState.value = currentState.copy(openProfile = updatedProfile)
            }
        }
    }

    fun listProfiles() {
        viewModelScope.launch(Dispatchers.IO)  {
            val currentState = _uiState.value

            _uiState.value = currentState.copy(
                profiles = dao.listAll()
            )
        }
    }

    fun formAction(formType: FormType) {
        val profile = uiState.value.openProfile
        viewModelScope.launch {
            when (formType) {
                FormType.CREATE -> createProfile(profile)
                FormType.EDIT -> editProfile(profile)
                FormType.DELETE -> deleteProfile(profile)
            }
        }
    }

    fun closeForm() {
        viewModelScope.launch {
            val currentState = _uiState.value

            _uiState.value = currentState.copy(
                formOpen = false
            )
        }
    }

    fun openForm(profile: MetronomeProfile) {
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                formOpen = true,
                openProfile = profile
            )
        }
    }

    fun toggleEditMode() {
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                deleteMode = false,
                editMode = !currentState.editMode
            )
        }
    }

    fun toggleDeleteMode() {
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                editMode = false,
                deleteMode = !currentState.deleteMode
            )
        }
    }

    private fun editProfile(profile: MetronomeProfile) {
        viewModelScope.launch {
            dao.update(profile)
        }
    }

    private fun createProfile(profile: MetronomeProfile) {
        viewModelScope.launch(Dispatchers.IO)  {
            dao.insert(profile)
        }
    }

    private fun deleteProfile(profile: MetronomeProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(profile)
        }
    }
}