package com.vinyla_android.data.di

import com.vinyla_android.data.di.annotation.VinylaMembersRemote
import com.vinyla_android.data.remote.source.VinylaMembersRemoteSource
import com.vinyla_android.data.remote.source.VinylsRemoteSource
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.data.source.VinylsSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Malibin
 * on 9월 14, 2021
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteSourceModule {

    @Binds
    @Singleton
    fun provideVinylsRemoteSource(source: VinylsRemoteSource): VinylsSource

    @Binds
    @Singleton
    @VinylaMembersRemote
    fun bindsVinylaMembersRemoteSource(source: VinylaMembersRemoteSource): VinylaMembersSource
}
