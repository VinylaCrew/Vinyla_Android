package com.vinyla_android.data.repository

import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.vinyl.SimpleVinyl
import com.vinyla_android.domain.entity.vinyl.Vinyl
import com.vinyla_android.domain.entity.vinyl.Vinyls
import com.vinyla_android.domain.repository.VinylsRepository
import javax.inject.Inject

/**
 * Created By Malibin
 * on 9월 03, 2021
 */

internal class RealVinylsRepository @Inject constructor(
    private val vinylsRemoteSource: VinylsSource,
) : VinylsRepository {

    override suspend fun getVinylOf(vinylId: Int): Vinyl? {
        return vinylsRemoteSource.getVinylOf(vinylId)
    }

    override suspend fun getCollectedVinyls(): Vinyls {
        return vinylsRemoteSource.getCollectedVinyls()
    }

    override suspend fun searchVinyls(query: String): List<SimpleVinyl> {
        return vinylsRemoteSource.searchVinyls(query)
    }

    override suspend fun collectVinyl(
        vinylId: Int,
        starScore: Float,
        comment: String
    ): Result<Unit> {
        return vinylsRemoteSource.collectVinyl(vinylId, starScore, comment)
    }

    override suspend fun cancelCollectVinyl(vinylId: Int): Result<Unit> {
        return vinylsRemoteSource.cancelCollectVinyl(vinylId)
    }

    override suspend fun getRepresentativeVinyl(): Vinyl? {
        return vinylsRemoteSource.getRepresentativeVinyl()
    }

    override suspend fun saveRepresentativeVinyl(vinylId: Int): Result<Unit> {
        return vinylsRemoteSource.saveRepresentativeVinyl(vinylId)
    }

    override suspend fun removeRepresentativeVinyl(): Result<Unit> {
        return vinylsRemoteSource.removeRepresentativeVinyl()
    }
}
