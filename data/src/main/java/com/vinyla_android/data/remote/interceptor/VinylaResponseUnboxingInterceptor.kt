package com.vinyla_android.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

/**
 * Created By Malibin
 * on 10월 10, 2021
 */

class VinylaResponseUnboxingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val jsonString = response.body?.string() ?: EMPTY_JSON
        val responseJson = JSONObject(jsonString)

        return response.newBuilder()
            .message(responseJson[KEY_MESSAGE].toString())
            .body(responseJson[KEY_DATA].toString().toResponseBody())
            .build()
    }

    companion object {
        private const val EMPTY_JSON = "{}"

        private const val KEY_MESSAGE = "message"
        private const val KEY_DATA = "data"
    }
}
