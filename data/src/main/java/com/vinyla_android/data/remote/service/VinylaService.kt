package com.vinyla_android.data.remote.service

import com.vinyla_android.data.remote.service.requestbody.CheckDuplicateNicknameRequestBody
import com.vinyla_android.data.remote.service.response.BaseResponse
import com.vinyla_android.data.remote.service.response.HomeResponse
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
    )

    // TODO: Token Interceptor 만들어야함
    @GET("/home")
    suspend fun getHomeInfo(): BaseResponse<HomeResponse>

    companion object {
        const val BASE_URL = ""
    }
}
