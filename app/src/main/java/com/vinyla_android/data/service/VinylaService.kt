package com.vinyla_android.data.service

import com.vinyla_android.data.service.requestbody.CheckDuplicateNicknameRequestBody
import com.vinyla_android.data.service.response.BaseResponse
import com.vinyla_android.data.service.response.HomeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created By Malibin
 * on 3월 28, 2021
 */

interface VinylaService {

    @POST("/users/check")
    suspend fun checkDuplicateNickname(
        @Body requestBody: CheckDuplicateNicknameRequestBody,
    ): Response<Unit>

    // TODO: Token Interceptor 만들어야함
    @GET("/home")
    suspend fun getHomeInfo(): Response<BaseResponse<HomeResponse>>

    companion object {
        const val BASE_URL = ""
    }
}
