package com.vinyla_android.data.service

import retrofit2.Response
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
        const val BASE_URL = "https://graph.facebook.com"
    }
}
