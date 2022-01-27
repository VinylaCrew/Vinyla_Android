package com.vinyla_android.data.remote.interceptor

import com.orhanobut.logger.Logger
import com.vinyla_android.domain.exception.UnexpectedServerError
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

/**
 * Created By Malibin
 * on 10월 10, 2021
 */

internal class VinylaResponseUnboxingInterceptor : Interceptor {

    // 아예 interceptor에서 가공해서 response를 가져오는것도 나쁘지 않지 않을가.
    // 인터넷 연결이 안되어있으면 애초에 proceed에서 exception이 발생해버림.
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val responseJson = response.extractResponseJson()

        val dataPayload = if (responseJson.has(KEY_DATA)) responseJson[KEY_DATA] else EMPTY_JSON
        return response.newBuilder()
            .message(responseJson[KEY_MESSAGE].toString())
            .body(dataPayload.toString().toResponseBody())
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
