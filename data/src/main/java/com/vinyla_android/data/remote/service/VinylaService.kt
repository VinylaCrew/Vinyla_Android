package com.vinyla_android.data.remote.service

import com.vinyla_android.data.remote.params.CheckNicknameParams
import com.vinyla_android.data.remote.params.CollectVinylParams
import com.vinyla_android.data.remote.params.SignUpParams
import com.vinyla_android.data.remote.response.CollectedVinylsResponse
import com.vinyla_android.data.remote.response.HomeResponse
import com.vinyla_android.data.remote.response.SearchVinylsResponse
import com.vinyla_android.data.remote.response.SignUpResponse
import com.vinyla_android.data.remote.response.VinylDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created By Malibin
 * on 3월 28, 2021
 */

internal interface VinylaService {

    @POST("/users/check")
    suspend fun checkNickname(
        @Body body: CheckNicknameParams,
    ): Response<Unit>

    @POST("/users/signup")
    suspend fun signUp(
        @Body body: SignUpParams,
    ): Response<SignUpResponse>

    // TODO: Token Interceptor 만들어야함
    @GET("/home")
    suspend fun getHomeInfo(): Response<HomeResponse>

    @POST("/vinyls")
    suspend fun collectVinyl(
        @Body body: CollectVinylParams,
    ): Response<Unit>

    @DELETE("/vinyls/{id}")
    suspend fun cancelCollectVinyl(
        @Path("id") vinylId: Int,
    ): Response<Unit>

    @GET("/vinyls/my")
    suspend fun getCollectedVinyls(): Response<CollectedVinylsResponse>

    @GET("/vinyls/search")
    suspend fun searchVinyls(
        @Query("q") query: String,
    ): Response<List<SearchVinylsResponse>>

    @GET("/vinyls/search/{id}")
    suspend fun getVinyl(
        @Path("id") vinylId: Int,
    ): Response<VinylDetailsResponse>

    companion object {
        const val BASE_URL = "http://13.209.245.76:3000"
    }
}
