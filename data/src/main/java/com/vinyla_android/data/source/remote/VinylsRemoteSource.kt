package com.vinyla_android.data.source.remote

import com.vinyla_android.data.remote.params.CollectVinylParams
import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.vinyl.SimpleVinyl
import com.vinyla_android.domain.entity.vinyl.Vinyl
import com.vinyla_android.domain.entity.vinyl.Vinyls
import com.vinyla_android.domain.exception.UnexpectedServerError
import javax.inject.Inject

/**
 * Created By Malibin
 * on 9월 03, 2021
 */

internal class VinylsRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylsSource {

    override suspend fun getVinylOf(vinylId: Int): Vinyl? {
        val response = vinylaService.getVinyl(vinylId)
        if (response.isSuccessful) return response.body()?.toVinyl()
        if (response.code() == 400) return null
        throw UnexpectedServerError()
    }

    override suspend fun getCollectedVinyls(): Vinyls {
        val response = vinylaService.getCollectedVinyls()
        return if (response.isSuccessful) {
            vinylaService.getCollectedVinyls().body()?.myVinyls.orEmpty()
                .map {
                    Vinyl(
                        id = it.vinylIdx,
                        title = it.title,
                        artistName = it.artist,
                        genre = emptyList(),
                        trackList = emptyList(),
                        releaseYear = -1,
                        starScore = -1f,
                        reviewCount = -1,
                        imageUrl = it.imageUrl,
                        thumbnailUrl = it.imageUrl,
                    )
                }
                .let { Vinyls.from(it) }
        } else throw UnexpectedServerError(response.message())
    }

    override suspend fun searchVinyls(query: String): List<SimpleVinyl> {
        if (query.isBlank()) return emptyList()
        val response = vinylaService.searchVinyls(query)
        if (response.isSuccessful) return response.body().orEmpty().map { it.toSimpleVinyl() }
        throw UnexpectedServerError()
    }

    override suspend fun collectVinyl(
        vinyl: Vinyl,
        starScore: Float,
        comment: String
    ): Result<Unit> {
        val params = CollectVinylParams(
            id = vinyl.id,
            title = vinyl.title,
            artist = vinyl.artistName,
            image = vinyl.imageUrl.orEmpty(),
            year = vinyl.releaseYear,
            genres = vinyl.genre,
            tracklist = vinyl.trackList.map { it.title },
            rate = (starScore * 2).toInt(),
            comment = comment,
        )
        val response = vinylaService.collectVinyl(params)
        if (response.isSuccessful) return Result.success(Unit)
        return Result.failure(UnexpectedServerError())
    }

    override suspend fun cancelCollectVinyl(vinylId: Int): Result<Unit> {
        val response = vinylaService.cancelCollectVinyl(vinylId)
        if (response.isSuccessful) return Result.success(Unit)
        return Result.failure(UnexpectedServerError())
    }

    override suspend fun getRepresentativeVinyl(): Vinyl? {
        TODO("Not yet implemented")
    }

    override suspend fun saveRepresentativeVinyl(vinylId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun removeRepresentativeVinyl(): Result<Unit> {
        TODO("Not yet implemented")
    }
}
