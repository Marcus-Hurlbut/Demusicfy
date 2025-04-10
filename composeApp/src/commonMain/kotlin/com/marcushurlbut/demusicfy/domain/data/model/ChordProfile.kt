package com.marcushurlbut.demusicfy.domain.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChordProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var chord: String,
    @ColumnInfo var intervals: String,
    @ColumnInfo var nickname: String = "",
    @ColumnInfo var rootString: Int = 0,
    @ColumnInfo var rootFret: Int = 0,
    @ColumnInfo var string1: Int? = null,
    @ColumnInfo var string2: Int? = null,
    @ColumnInfo var string3: Int? = null,
    @ColumnInfo var string4: Int? = null,
    @ColumnInfo var string5: Int? = null,
    @ColumnInfo var string6: Int? = null,
)