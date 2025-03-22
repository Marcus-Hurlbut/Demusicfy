package com.marcushurlbut.demusicfy.domain

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

typealias PrefsDataStore = DataStore<Preferences>
fun createDataStore(producePath: () -> String): PrefsDataStore =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )
const val dataStoreFileName = "demusicfy_app_preferences.preferences_pb"
//@Composable
expect fun rememberDataStore(): PrefsDataStore
