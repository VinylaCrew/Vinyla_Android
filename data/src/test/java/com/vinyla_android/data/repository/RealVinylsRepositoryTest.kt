package com.vinyla_android.data.repository

import com.google.common.truth.Truth.assertThat
import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.repository.VinylsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
            name = "name",
            artiestName = "artist",
            genre = "pop",
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
            artiestName = "The Chainsmokers",
            thumbnailUrl = "",
        )
        coEvery { vinylsRemoteSource.searchVinyls("Memories") } returns listOf(expectedVinyl)

        // when
        val retrieveVinyls = realVinylsRepository.searchVinyls("Memories")

        // then
        assertThat(retrieveVinyls).containsExactlyElementsIn(listOf(expectedVinyl))
    }
}
