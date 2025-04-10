package com.marcushurlbut.demusicfy.domain.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.marcushurlbut.demusicfy.domain.data.model.ChordProfile

@Dao
interface ChordProfileDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg chordProfile: ChordProfile)

    @Update
    suspend fun update(profile: ChordProfile)

    @Delete
    suspend fun delete(profile: ChordProfile)

    @Query("SELECT * FROM chordProfile")
    suspend fun listAll(): List<ChordProfile>
}