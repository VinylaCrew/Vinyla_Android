package com.vinyla_android.data.di

import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.remote.source.VinylsRemoteSource
import com.vinyla_android.data.source.VinylsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Malibin
 * on 9월 14, 2021
 */

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Singleton
    @Provides
    fun provideVinylsRemoteSource(
        vinylaService: VinylaService,
    ): VinylsSource {
        return VinylsRemoteSource(vinylaService)
    }
}
