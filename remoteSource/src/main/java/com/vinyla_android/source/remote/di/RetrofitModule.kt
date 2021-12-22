package com.vinyla_android.source.remote.di

import com.vinyla_android.source.remote.VinylaResponseUnboxingInterceptor
import com.vinyla_android.source.remote.interceptor.HTTP_LOGGING_INTERCEPTOR
import com.vinyla_android.source.remote.service.VinylaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created By Malibin
 * on 3월 28, 2021
 */

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Singleton
    @Provides
    fun provideVinylaService(): VinylaService {
        return Retrofit.Builder()
            .baseUrl(VinylaService.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VinylaService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HTTP_LOGGING_INTERCEPTOR)
            .addInterceptor(VinylaResponseUnboxingInterceptor())
            .build()
    }
}