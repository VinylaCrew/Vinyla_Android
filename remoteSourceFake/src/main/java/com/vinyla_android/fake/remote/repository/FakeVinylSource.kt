package com.vinyla_android.fake.remote.repository

import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.entity.Vinyl
import java.util.*
import javax.inject.Inject

internal class FakeVinylSource @Inject constructor() : VinylsSource {

    override suspend fun getVinylOf(vinylId: Int): Vinyl? {
        return STUB_VINYL
    }

    override suspend fun searchVinyls(query: String): List<SimpleVinyl> {
        return listOf(
            SimpleVinyl(
                id = 73,
                thumbnailUrl = "",
                title = "바이닐",
                artiestName = "바 이닐 씨"
            )
        )
    }

    companion object {
        val STUB_VINYL = Vinyl(
            id = 73,
            name = "바이닐",
            artiestName = "바 이닐 씨",
            genre = "장르",
            releaseYear = 1995,
            starScore = 5.0f,
            reviewCount = 37,
            imageUrl = null,
            thumbnailUrl = null,
            isCollected = false,
            collectedDate = Date(),
        )
    }
}
