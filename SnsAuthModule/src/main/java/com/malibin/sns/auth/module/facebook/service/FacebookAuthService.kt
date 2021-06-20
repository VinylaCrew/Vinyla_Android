package com.malibin.sns.auth.module.facebook.service

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created By Malibin
 * on 4월 21, 2021
 */

interface FacebookAuthService {

    @DELETE("/{userId}/permissions")
    suspend fun unLink(
        @Path("userId") userId: String,
        @Query("access_token") accessToken: String,
    ): Response<Unit>

    companion object {
        private const val BASE_URL = "https://graph.facebook.com"

        private var instance: FacebookAuthService? = null

        fun getInstance(): FacebookAuthService {
            return synchronized(this) {
                instance ?: createInstance().also { instance = it }
            }
        }

        private fun createInstance(): FacebookAuthService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FacebookAuthService::class.java)
        }
    }
}
