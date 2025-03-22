package com.marcushurlbut.demusicfy.domain.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

class Note(
    note: String = "",
    pressed: Boolean = false,
    color: Color = Color(0xFF4E3629),
    stringNum: Int = 0,
    fretNum: Int = 0,
    index: Int = 0
) {
    private val noteState = mutableStateOf(note)
    private val indexState = mutableStateOf(index)
    private val pressedState = mutableStateOf(pressed)
    private val colorState = mutableStateOf(color)
    private val stringNumState = mutableStateOf(stringNum)
    private val fretNumState = mutableStateOf(fretNum)

    fun setIndex(index: Int) {
        indexState.value = index
    }

    fun getIndex(): Int {
        return indexState.value
    }

    fun setNote(newNote: String) {
        noteState.value = newNote
    }

    fun getNote(): String {
        return noteState.value
    }

    fun setPressed(isPressed: Boolean) {
        pressedState.value = isPressed
    }

    fun isPressed(): Boolean {
        return pressedState.value
    }

    fun setColor(color: Color) {
        colorState.value = color
    }

    fun getColor(): Color {
        return colorState.value
    }

    fun setStringNum(stringNum: Int) {
        this.stringNumState.value = stringNum
    }

    fun getStringNum(): Int {
        return stringNumState.value
    }

    fun setFretNum(fretNum: Int) {
        this.stringNumState.value = fretNum
    }

    fun getFretNum(): Int {
        return fretNumState.value
    }

    fun getLocation() : IntArray{
        return intArrayOf(stringNumState.value, fretNumState.value)
    }
}