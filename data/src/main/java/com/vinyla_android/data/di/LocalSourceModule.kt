package com.vinyla_android.data.di

import com.vinyla_android.data.di.annotation.VinylMembersLocal
import com.vinyla_android.data.local.source.VinylaMembersLocalSource
import com.vinyla_android.data.source.VinylaMembersSource
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
internal object LocalSourceModule {

    @Singleton
    @Provides
    @VinylMembersLocal
    fun provideVinylsMembersLocalSource(): VinylaMembersSource {
        return VinylaMembersLocalSource()
    }
}
