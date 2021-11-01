package com.vinyla_android.source.local.di

import com.vinyla_android.data.di.annotation.VinylaMembersLocal
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.source.local.source.VinylaMembersLocalSource
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
internal interface LocalSourceModule {

    @Singleton
    @Binds
    @VinylaMembersLocal
    fun bindVinylaMembersLocalSource(source: VinylaMembersLocalSource): VinylaMembersSource
}
