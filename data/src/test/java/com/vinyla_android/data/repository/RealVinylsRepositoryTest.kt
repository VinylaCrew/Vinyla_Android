package com.vinyla_android.data.repository

import com.google.common.truth.Truth.assertThat
import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.repository.VinylsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class RealVinylsRepositoryTest {

    private lateinit var realVinylsRepository: VinylsRepository
    private lateinit var vinylsRemoteSource: VinylsSource

    @BeforeEach
    fun setUp() {
        vinylsRemoteSource = mockk(relaxed = true)
        realVinylsRepository = RealVinylsRepository(vinylsRemoteSource)
    }

    @Test
    fun `아이디로 바이닐 정보를 불러온다`(): Unit = runBlocking {
        // given
        val expectedVinyl = Vinyl(
            id = 1,
            title = "name",
            artistName = "artist",
            genre = "pop",
            trackList = emptyList(),
            imageUrl = "",
            thumbnailUrl = "",
            releaseYear = 1995,
            starScore = 0.0f,
            reviewCount = 0,
        )
        coEvery { vinylsRemoteSource.getVinylOf(1) } returns expectedVinyl

        // when
        val retrieveVinyl = realVinylsRepository.getVinylOf(1)

        // then
        assertThat(retrieveVinyl).isEqualTo(expectedVinyl)
    }

    @Test
    fun `검색어로 바이닐을 검색할 수 있다`(): Unit = runBlocking {
        // given
        val expectedVinyl = SimpleVinyl(
            id = 1,
            title = "Memories...Do Not Open",
            artistName = "The Chainsmokers",
            thumbnailUrl = "",
        )
        coEvery { vinylsRemoteSource.searchVinyls("Memories") } returns listOf(expectedVinyl)

        // when
        val retrieveVinyls = realVinylsRepository.searchVinyls("Memories")

        // then
        assertThat(retrieveVinyls).containsExactlyElementsIn(listOf(expectedVinyl))
    }

    @Test
    fun `바이닐을 수집할 수 있다`(): Unit = runBlocking {
        // given
        val expectedVinyl = mockk<Vinyl> {
            every { id } returns 1
            every { isCollected } returns true
        }
        val expectedCollectResult = Result.success(Unit)
        coEvery { vinylsRemoteSource.collectVinyl(1, any(), any()) } returns expectedCollectResult
        coEvery { vinylsRemoteSource.getVinylOf(1) } returns expectedVinyl

        // when
        val actualCollectResult = realVinylsRepository.collectVinyl(1, 10.0f, "comment")
        val actualVinyl = realVinylsRepository.getVinylOf(1)

        // then
        assertAll(
            { assertThat(actualVinyl?.isCollected).isTrue() },
            { assertThat(actualCollectResult).isEqualTo(expectedCollectResult) },
        )
    }

    @Test
    fun `수집한 바이닐을 수집 취소할 수 있다`(): Unit = runBlocking {
        val expectedVinyl = mockk<Vinyl> {
            every { id } returns 1
            every { isCollected } returns false
        }
        val expectedCollectResult = Result.success(Unit)
        coEvery { vinylsRemoteSource.cancelCollectVinyl(1) } returns expectedCollectResult
        coEvery { vinylsRemoteSource.getVinylOf(1) } returns expectedVinyl

        // when
        val collectResult = realVinylsRepository.cancelCollectVinyl(1)
        val actualVinyl = realVinylsRepository.getVinylOf(1)

        // then
        assertAll(
            { assertThat(actualVinyl?.isCollected).isFalse() },
            { assertThat(collectResult).isEqualTo(Result.success(Unit)) },
        )
    }

    @Test
    fun `바이닐을 대표 바이닐로 설정할 수 있다`(): Unit = runBlocking {
        // given
        val expectedVinyl = mockk<Vinyl> {
            every { id } returns 1
            every { isRepresentative } returns true
        }
        val expectedResult = Result.success(Unit)
        coEvery { vinylsRemoteSource.saveRepresentativeVinyl(1) } returns expectedResult
        coEvery { vinylsRemoteSource.getRepresentativeVinyl() } returns expectedVinyl

        // when
        val collectResult = realVinylsRepository.saveRepresentativeVinyl(1)
        val actualVinyl = realVinylsRepository.getRepresentativeVinyl()

        // then
        assertAll(
            { assertThat(actualVinyl?.isRepresentative).isTrue() },
            { assertThat(collectResult).isEqualTo(Result.success(Unit)) },
        )
    }

    @Test
    fun `대표 바이닐의 대표 설정을 취소할 수 있다`(): Unit = runBlocking {
        // given
        val expectedResult = Result.success(Unit)
        coEvery { vinylsRemoteSource.removeRepresentativeVinyl() } returns expectedResult
        coEvery { vinylsRemoteSource.getRepresentativeVinyl() } returns null

        // when
        val collectResult = realVinylsRepository.removeRepresentativeVinyl()
        val actualVinyl = realVinylsRepository.getRepresentativeVinyl()

        // then
        assertAll(
            { assertThat(actualVinyl).isNull() },
            { assertThat(collectResult).isEqualTo(Result.success(Unit)) },
        )
    }

    @Test
    fun `기존 대표 바이닐이 있을 때 새 대표 바이닐을 등록하면, 새로운 대표 바이닐을 반환한다`(): Unit = runBlocking {
        // given
        val previousVinyl = mockk<Vinyl> {
            every { id } returns 1
            every { isRepresentative } returns true
        }
        val newVinyl = mockk<Vinyl> {
            every { id } returns 2
            every { isRepresentative } returns true
        }

        realVinylsRepository.saveRepresentativeVinyl(1)
        coEvery { vinylsRemoteSource.getRepresentativeVinyl() } returns previousVinyl

        // when 기존에 등록해 둔 대표 바이닐
        val previousRepresentativeVinyl = realVinylsRepository.getRepresentativeVinyl()

        // then
        assertThat(previousRepresentativeVinyl?.isRepresentative).isTrue()

        // when
        realVinylsRepository.saveRepresentativeVinyl(2)
        coEvery { vinylsRemoteSource.getRepresentativeVinyl() } returns newVinyl
        val newRepresentativeVinyl = realVinylsRepository.getRepresentativeVinyl()

        // then
        assertAll(
            { assertThat(newRepresentativeVinyl?.isRepresentative).isTrue() },
        )
    }
}
