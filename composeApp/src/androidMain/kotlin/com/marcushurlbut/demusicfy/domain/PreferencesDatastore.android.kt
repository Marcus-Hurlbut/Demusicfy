package com.marcushurlbut.demusicfy.domain

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.marcushurlbut.demusicfy.MainActivity
import java.io.File

//actual fun preferencesDataStore(filePath: String) : DataStore<Preferences> =
//    PreferenceDataStoreFactory.create(
//        produceFile = { File(getContext().filesDir, filePath) }
//    )

//fun preferencesDataStore(context: Context): DataStore<Preferences> = preferencesDataStore(
////    producePath = { context.filesDir.resolve(dataStoreFileName).path },
//    producePath = { File(getContext().filesDir, dataStoreFileName).toString() },
//)

//@Composable
actual fun rememberDataStore(): PrefsDataStore {
    val context = getContext()
    return createDataStore(
            producePath = {
                context.filesDir.resolve(dataStoreFileName).absolutePath
            },
        )
}

fun getContext() : Context {
    return MainActivity.context!!
}
