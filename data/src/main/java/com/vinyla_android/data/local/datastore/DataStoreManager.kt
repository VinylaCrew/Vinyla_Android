package com.vinyla_android.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first


internal class DataStoreManager internal constructor(
    private val datastore: DataStore<Preferences>
) {
    internal constructor(applicationContext: Context) : this(applicationContext.dataStore)

    suspend fun getString(key: String): String? {
        return datastore[stringPreferencesKey(key)].first()
    }

    suspend fun saveString(key: String, value: String) {
        datastore.edit { it[stringPreferencesKey(key)] = value }
    }
}
