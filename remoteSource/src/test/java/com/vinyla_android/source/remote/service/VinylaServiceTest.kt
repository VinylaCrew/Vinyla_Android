package com.vinyla_android.source.remote.service

import com.google.common.truth.Truth.assertThat
import com.vinyla_android.source.remote.params.CheckNicknameParams
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal class VinylaServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var githubService: VinylaService

    @BeforeEach
    fun setUp() {
        val interceptor = com.vinyla_android.source.remote.VinylaResponseUnboxingInterceptor()
        mockWebServer = MockWebServer()
        githubService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .baseUrl(mockWebServer.url(""))
            .build()
            .create()
    }

    @Test
    fun `닉네임 중복 체크 - 중복인 경우 테스트`() = runBlocking {
        // given
        val response = MockResponse()
            .setResponseCode(400)
            .setBody(
                """
                {
                    "status": 400,
                    "success": false,
                    "message": "사용 중인 닉네임입니다."
                }
            """.trimIndent()
            )
        mockWebServer.enqueue(response)

        // when
        val actualResponse = githubService.checkNickname(
            CheckNicknameParams(
                "dup"
            )
        )

        // then
        assertAll(
            { assertThat(actualResponse.isSuccessful).isFalse() },
            { assertThat(actualResponse.message()).isEqualTo("사용 중인 닉네임입니다.") },
            { assertThat(actualResponse.errorBody()?.string()).isEqualTo("{}") },
            { assertThat(actualResponse.body()).isNull() },
        )
    }

    @Test
    fun `닉네임 중복 체크 - 유니크 한 경우 테스트`() = runBlocking {
        // given
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                    "status": 200,
                    "success": true,
                    "message": "사용 가능한 닉네임입니다.",
                    "data": {
                        "nickname": "unique"
                    }
                }
            """.trimIndent()
            )
        mockWebServer.enqueue(response)

        // when
        val actualResponse = githubService.checkNickname(
            CheckNicknameParams(
                "dup"
            )
        )

        // then
        assertAll(
            { assertThat(actualResponse.isSuccessful).isTrue() },
            { assertThat(actualResponse.message()).isEqualTo("사용 가능한 닉네임입니다.") },
            { assertThat(actualResponse.errorBody()).isNull() },
            { assertThat(actualResponse.body()).isEqualTo(Unit) },
        )
    }
}
