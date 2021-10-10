package com.vinyla_android.data.remote.service

import com.vinyla_android.data.remote.service.params.CheckNicknameParams
import com.vinyla_android.data.remote.service.params.SignUpParams
import com.vinyla_android.data.remote.service.response.HomeResponse
import com.vinyla_android.data.remote.service.response.SearchingVinylResponse
import com.vinyla_android.data.remote.service.response.SignUpResponse
import com.vinyla_android.data.remote.service.response.VinylDetailsResponse
import com.vinyla_android.data.remote.service.response.VinylaResponse
import retrofit2.Response
import retrofit2.http.Body
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
    ): Response<VinylaResponse<Boolean>>

    @POST("/users/signup")
    suspend fun signUp(
        @Body body: SignUpParams,
    ): Response<VinylaResponse<SignUpResponse>>

    // TODO: Token Interceptor 만들어야함
    @GET("/home")
    suspend fun getHomeInfo(): VinylaResponse<HomeResponse>

    @GET("/vinyls/search/detail/{id}")
    suspend fun getVinyl(
        @Path("id") vinylId: Int,
    ): Response<VinylaResponse<VinylDetailsResponse>>

    @GET("/vinyls/search")
    suspend fun searchVinyls(
        @Query("q") query: String,
    ): Response<VinylaResponse<List<SearchingVinylResponse>>>

    companion object {
        const val BASE_URL = ""
    }
}
