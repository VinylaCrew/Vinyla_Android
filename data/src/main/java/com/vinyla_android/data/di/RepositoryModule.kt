package com.vinyla_android.data.di

import com.vinyla_android.data.repository.RealVinylsRepository
import com.vinyla_android.domain.repository.VinylsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Malibin
 * on 9월 14, 2021
 */

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindVinylsRepository(repository: RealVinylsRepository): VinylsRepository
}
