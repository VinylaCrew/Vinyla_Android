package com.vinyla_android.source.remote.source

import com.google.common.truth.Truth.assertThat
import com.vinyla_android.domain.entity.vinyl.SimpleVinyl
import com.vinyla_android.domain.entity.vinyl.Vinyl
import com.vinyla_android.source.remote.response.SearchVinylsResponse
import com.vinyla_android.source.remote.response.VinylDetailsResponse
import com.vinyla_android.source.remote.service.VinylaService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import retrofit2.Response

internal class VinylsRemoteSourceTest {

    private lateinit var vinylaService: VinylaService
    private lateinit var vinylsRemoteSource: VinylsRemoteSource

    @BeforeEach
    fun setUp() {
        vinylaService = mockk(relaxed = true)
        vinylsRemoteSource = VinylsRemoteSource(vinylaService)
    }

    @Test
    fun `id로 바이닐을 가져올 수 있다`(): Unit = runBlocking {
        // given
        val vinylResponse = VinylDetailsResponse(
            id = 1111,
            title = "꽃갈피",
            artistName = "IU",
            imageUrl = "https://mblogthumb-phinf.pstatic.net/20150819_112/cjculture_azit_1439975458685YBatS_JPEG/2._%BE%C6%C0%CC%C0%AF_%B8%AE%B8%DE%C0%CC%C5%A9.jpg?type=w2",
            year = 2014,
            rate = 5.0f,
            rateCount = 37,
            genres = listOf("발라드/R&B"),
            tracks = emptyList(),
        )
        coEvery { vinylaService.getVinyl(1111) } returns Response.success(vinylResponse)

        val expectedVinyl = Vinyl(
            id = 1111,
            title = "꽃갈피",
            artistName = "IU",
            genre = "발라드/R&B",
            trackList = emptyList(),
            releaseYear = 2014,
            starScore = 5.0f,
            reviewCount = 37,
            imageUrl = "https://mblogthumb-phinf.pstatic.net/20150819_112/cjculture_azit_1439975458685YBatS_JPEG/2._%BE%C6%C0%CC%C0%AF_%B8%AE%B8%DE%C0%CC%C5%A9.jpg?type=w2",
            thumbnailUrl = "https://mblogthumb-phinf.pstatic.net/20150819_112/cjculture_azit_1439975458685YBatS_JPEG/2._%BE%C6%C0%CC%C0%AF_%B8%AE%B8%DE%C0%CC%C5%A9.jpg?type=w2",
        )

        // when
        val actualVinyl = vinylsRemoteSource.getVinylOf(1111)

        // then
        assertThat(actualVinyl).isEqualTo(expectedVinyl)
    }

    @Test
    fun `존재하지 않는 id로 바이닐을 요청하면 null을 반환한다`(): Unit = runBlocking {
        // given
        val response = Response.error<VinylDetailsResponse>(
            400,
            "".toResponseBody("application/json".toMediaType())
        )
        coEvery { vinylaService.getVinyl(any()) } returns response

        // when
        val actualVinyl = vinylsRemoteSource.getVinylOf(-1)

        // then
        assertThat(actualVinyl).isNull()
    }

    @Test
    fun `키워드로 바이닐을 검색할 수 있다`(): Unit = runBlocking {
        // given
        val searchResponse = Response.success(
            listOf(
                SearchVinylsResponse(
                    vinylId = 1,
                    thumbnailUrl = "",
                    title = "꽃갈피",
                    artistName = "IU",
                )
            )
        )
        coEvery { vinylaService.searchVinyls("IU") } returns searchResponse

        val expectedSearchedVinyls = listOf(
            SimpleVinyl(
                id = 1,
                thumbnailUrl = "",
                title = "꽃갈피",
                artistName = "IU",
            )
        )

        // when
        val actualSearchedVinyls = vinylsRemoteSource.searchVinyls("IU")

        // then
        assertThat(actualSearchedVinyls).containsExactlyElementsIn(expectedSearchedVinyls)
    }

    @Test
    fun `공백으로 검색하면 서버 요청을 하지 않고 빈 리스트를 반환한다`(): Unit = runBlocking {
        // when
        val actualSearchedVinyls = vinylsRemoteSource.searchVinyls("")

        // then
        assertAll(
            { coVerify(exactly = 0) { vinylaService.searchVinyls(any()) } },
            { assertThat(actualSearchedVinyls).isEmpty() },
        )
    }

    @Test
    fun `바이닐을 수집할 수 있다`(): Unit = runBlocking {
        // given
        val response = Response.success(Unit)
        coEvery { vinylaService.collectVinyl(any()) } returns response

        val expectedResult = Result.success(Unit)

        // when
        val actualResult = vinylsRemoteSource.collectVinyl(1, 5.0f, "코멘트")

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `바이닐을 수집을 취소할 수 있다`(): Unit = runBlocking {
        // given
        val response = Response.success(Unit)
        coEvery { vinylaService.cancelCollectVinyl(any()) } returns response

        val expectedResult = Result.success(Unit)

        // when
        val actualResult = vinylsRemoteSource.cancelCollectVinyl(1)

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }
}
