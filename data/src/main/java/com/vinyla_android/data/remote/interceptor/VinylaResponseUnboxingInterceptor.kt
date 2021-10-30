package com.vinyla_android.data.remote.interceptor

import com.orhanobut.logger.Logger
import com.vinyla_android.data.exception.UnexpectedServerError
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
        val responseJson = response.extractResponseJson()
        return response.newBuilder()
            .message(responseJson[KEY_MESSAGE].toString())
            .body(responseJson[KEY_DATA].toString().toResponseBody())
            .build()
    }

    private fun Response.extractResponseJson(): JSONObject {
        val jsonString = this.body?.string() ?: EMPTY_JSON
        return try {
            JSONObject(jsonString)
        } catch (exception: Exception) {
            Logger.d(
                "VinylaResponseUnboxingInterceptor",
                "서버 응답이 json이 아님 : $jsonString"
            )
            throw UnexpectedServerError(cause = exception)
        }
    }

    companion object {
        private const val EMPTY_JSON = "{}"

        private const val KEY_MESSAGE = "message"
        private const val KEY_DATA = "data"
    }
}
