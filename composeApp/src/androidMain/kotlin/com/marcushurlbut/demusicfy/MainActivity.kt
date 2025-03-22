package com.marcushurlbut.demusicfy

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.marcushurlbut.demusicfy.domain.data.database.AppDatabase
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        val database = getAppDatabase(applicationContext)
        setContent {
            App(databaseBuilder = database)
        }
    }

    @SuppressLint("StaticFieldLeak")
    companion object {
        var context: Context? = null
    }

    private fun getAppDatabase(context: Context): RoomDatabase.Builder<AppDatabase> {
        return Room.databaseBuilder<AppDatabase>(
            context = context.applicationContext,
            name = "demusicfy_app.db"
        )
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
    }


}