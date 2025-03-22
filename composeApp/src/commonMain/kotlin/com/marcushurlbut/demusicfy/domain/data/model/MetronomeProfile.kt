package com.marcushurlbut.demusicfy.domain.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MetronomeProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var song: String,
    var artist: String,
    var bpm: Int,
)