package com.marcushurlbut.demusicfy.domain.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.marcushurlbut.demusicfy.domain.data.model.MetronomeProfile

@Dao
interface MetronomeProfileDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg metronomeProfile: MetronomeProfile)

    @Update
    suspend fun update(profile: MetronomeProfile)

    @Delete
    suspend fun delete(profile: MetronomeProfile)

    @Query("SELECT * FROM metronomeProfile")
    suspend fun listAll(): List<MetronomeProfile>
}