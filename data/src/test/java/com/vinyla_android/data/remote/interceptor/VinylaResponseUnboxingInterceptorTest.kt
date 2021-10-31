package com.vinyla_android.data.remote.interceptor

import com.google.common.truth.Truth.assertThat
import com.vinyla_android.data.exception.UnexpectedServerError
import io.mockk.every
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.math.exp

internal class VinylaResponseUnboxingInterceptorTest {

    private lateinit var chain: Interceptor.Chain
    private lateinit var interceptor: VinylaResponseUnboxingInterceptor

    @BeforeEach
    fun setUp() {
        chain = mockk(relaxed = true)
        interceptor = VinylaResponseUnboxingInterceptor()
    }

    @Test
    fun `응답 성공 시 Response body를 서버 응답 Json의 data value의 내용으로 변형한다`() {
        // given
        val successRawResponseJson = """
            {
                "status": 200,
                "success": true,
                "message": "response message blar blar",
                "data": {
                   "someKey": "awesome",
                   "otherKey": "otherValue"
                }
            }
        """.trimIndent()
        val successResponseBody =
            successRawResponseJson.toResponseBody("application/json".toMediaType())
        val successResponse = Response.Builder()
            .code(200)
            .body(successResponseBody)
            .request(Request.Builder().url("https://localhost:8080/").build())
            .protocol(Protocol.HTTP_1_1)
            .message("mockedMessage")
            .build()

        every { chain.proceed(any()) } returns successResponse

        val expectedRawResponseJson = JSONObject(
            """
            {
               "someKey": "awesome",
               "otherKey": "otherValue"
            }
        """.trimIndent()
        ).toString()
        val expectedResponseBody =
            expectedRawResponseJson.toResponseBody("application/json".toMediaType())

        // when
        val actualResponse: Response = interceptor.intercept(chain)

        // then
        assertAll(
            { assertThat(actualResponse.body?.string()).isEqualTo(expectedResponseBody.string()) },
            { assertThat(actualResponse.message).isEqualTo("response message blar blar") },
        )
    }

    @Test
    fun `서버에서 json이 아닌 응답이 오면 Exception`() {
        // given
        val failRawResponseJson = """
            <html>
                blar blar
            </html>
        """.trimIndent()
        val failResponseBody =
            failRawResponseJson.toResponseBody("application/json".toMediaType())
        val failResponse = Response.Builder()
            .code(400)
            .body(failResponseBody)
            .request(Request.Builder().url("https://localhost:8080/").build())
            .protocol(Protocol.HTTP_1_1)
            .message("mockedMessage")
            .build()

        every { chain.proceed(any()) } returns failResponse

        // when
        val exception = runCatching { interceptor.intercept(chain) }.exceptionOrNull()

        // then
        assertAll(
            { assertThat(exception).isInstanceOf(UnexpectedServerError::class.java) },
            { assertThat(exception).hasMessageThat().contains("알 수 없는 에러가 발생했습니다.") }
        )
    }

    //응답실패시 data 못가져오는거
}
