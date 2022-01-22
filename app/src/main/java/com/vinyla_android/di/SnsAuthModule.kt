package com.vinyla_android.di

import com.malibin.sns.auth.SnsAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SnsAuthModule {

    @Singleton
    @Provides
    fun providesDataStore(): SnsAuth {
        return SnsAuth.getInstance()
    }
}
