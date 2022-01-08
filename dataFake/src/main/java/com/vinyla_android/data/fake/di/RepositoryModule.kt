package com.vinyla_android.data.fake.di

import com.vinyla_android.data.fake.repository.FakeVinylaMembersRepository
import com.vinyla_android.data.fake.repository.FakeVinylsRepository
import com.vinyla_android.domain.repository.VinylaMembersRepository
import com.vinyla_android.domain.repository.VinylsRepository
import dagger.Binds
import dagger.Module
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
    fun bindVinylsRepository(repository: FakeVinylsRepository): VinylsRepository

    @Binds
    @Singleton
    fun bindVinylaMembersRepository(repository: FakeVinylaMembersRepository): VinylaMembersRepository
}
