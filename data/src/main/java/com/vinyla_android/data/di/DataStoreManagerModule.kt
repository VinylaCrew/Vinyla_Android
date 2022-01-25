package com.vinyla_android.data.di

import android.content.Context
import com.vinyla_android.data.local.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreManagerModule {

    @Singleton
    @Provides
    fun provideVinylaService(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}
