package com.vinyla_android.fake.remote.di

import com.vinyla_android.data.di.annotation.VinylaMembersRemote
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.fake.remote.repository.FakeVinylSource
import com.vinyla_android.fake.remote.repository.FakeVinylaMembersSource
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
    fun bindsFakeVinylsSource(source: FakeVinylSource): VinylsSource

    @Binds
    @Singleton
    @VinylaMembersRemote
    fun bindsFakeVinylaMembersSource(source: FakeVinylaMembersSource): VinylaMembersSource
}
