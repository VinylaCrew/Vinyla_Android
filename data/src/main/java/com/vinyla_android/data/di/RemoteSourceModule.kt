package com.vinyla_android.data.di

import com.vinyla_android.data.di.annotation.VinylMembersRemote
import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.remote.source.VinylaMembersRemoteSource
import com.vinyla_android.data.remote.source.VinylsRemoteSource
import com.vinyla_android.data.source.VinylaMembersSource
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
internal object RemoteSourceModule {

    @Singleton
    @Provides
    fun provideVinylsRemoteSource(
        vinylaService: VinylaService,
    ): VinylsSource {
        return VinylsRemoteSource(vinylaService)
    }

    @Singleton
    @Provides
    @VinylMembersRemote
    fun provideVinylsMembersRemoteSource(
        vinylaService: VinylaService,
    ): VinylaMembersSource {
        return VinylaMembersRemoteSource(vinylaService)
    }
}
