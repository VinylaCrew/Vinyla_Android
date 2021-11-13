package com.vinyla_android.source.remote.source

import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.entity.Vinyls
import com.vinyla_android.domain.exception.UnexpectedServerError
import com.vinyla_android.source.remote.params.CollectVinylParams
import com.vinyla_android.source.remote.service.VinylaService
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
        TODO("Not yet implemented")
    }

    override suspend fun searchVinyls(query: String): List<SimpleVinyl> {
        if (query.isBlank()) return emptyList()
        val response = vinylaService.searchVinyls(query)
        if (response.isSuccessful) return response.body().orEmpty().map { it.toSimpleVinyl() }
        throw UnexpectedServerError()
    }

    override suspend fun collectVinyl(
        vinylId: Int,
        starScore: Float,
        comment: String
    ): Result<Unit> {
        val params = CollectVinylParams(vinylId, starScore, comment)
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
