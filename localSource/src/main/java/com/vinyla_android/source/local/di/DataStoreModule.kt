package com.vinyla_android.source.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.dataStore by preferencesDataStore(name = "VinylaMemberTokenDB")

    @Singleton
    @Provides
    fun providesDataStore(
        @ApplicationContext applicationContext: Context,
    ): DataStore<Preferences> {
        return applicationContext.dataStore
    }
}
