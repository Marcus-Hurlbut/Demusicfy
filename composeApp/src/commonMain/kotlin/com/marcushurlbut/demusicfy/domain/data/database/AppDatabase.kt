package com.marcushurlbut.demusicfy.domain.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.marcushurlbut.demusicfy.domain.data.dao.MetronomeProfileDAO
import com.marcushurlbut.demusicfy.domain.data.model.MetronomeProfile


@Database(entities = [
    MetronomeProfile::class,
], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun metronomeProfileDAO(): MetronomeProfileDAO
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}