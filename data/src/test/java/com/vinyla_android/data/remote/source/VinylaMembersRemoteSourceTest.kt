package com.vinyla_android.data.remote.source

import com.google.common.truth.Truth.assertThat
import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.remote.service.params.CheckNicknameParams
import com.vinyla_android.domain.member.NicknameStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

internal class VinylaMembersRemoteSourceTest {

    private lateinit var vinylaService: VinylaService
    private lateinit var vinylaMembersRemoteSource: VinylaMembersRemoteSource

    @BeforeEach
    fun setUp() {
        vinylaService = mockk(relaxed = true)
        vinylaMembersRemoteSource = VinylaMembersRemoteSource(vinylaService)
    }

    @Test
    fun `닉네임이 중복되면 Duplicated Nickname 상태를 반환한다`(): Unit = runBlocking {
        // given
        val params = CheckNicknameParams("dup")

        val response = Response.error<Unit>(
            400,
            "{}".toResponseBody("application/json".toMediaType())
        )
        coEvery { vinylaService.checkNickname(params) } returns response

        // when
        val result = vinylaMembersRemoteSource.checkNickname("dup")

        // then
        assertThat(result).isEqualTo(NicknameStatus.DUPLICATED)
    }

    @Test
    fun `닉네임이 유니크하면 AVAILABLE Nickname 상태를 반환한다`(): Unit = runBlocking {
        // given
        val params = CheckNicknameParams("unique")

        val response = Response.success<Unit>(Unit)
        coEvery { vinylaService.checkNickname(params) } returns response

        // when
        val result = vinylaMembersRemoteSource.checkNickname("unique")

        // then
        assertThat(result).isEqualTo(NicknameStatus.AVAILABLE)
    }
}
