package com.marcushurlbut.demusicfy.domain


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import platform.Foundation.NSFileManager
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSUserDomainMask
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL

//@OptIn(ExperimentalForeignApi::class)
//fun preferencesDataStore(): DataStore<Preferences> = preferencesDataStore(
//    producePath = {
//        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
//            directory = NSDocumentDirectory,
//            inDomain = NSUserDomainMask,
//            appropriateForURL = null,
//            create = false,
//            error = null,
//        )
//        requireNotNull(documentDirectory).path + "/$dataStoreFileName"
//    }
//)


@OptIn(ExperimentalForeignApi::class)
//@Composable
actual fun rememberDataStore(): PrefsDataStore {
        return createDataStore(
            producePath = {
                val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null,
                )
                requireNotNull(documentDirectory).path + "/$dataStoreFileName"
            },
        )

}