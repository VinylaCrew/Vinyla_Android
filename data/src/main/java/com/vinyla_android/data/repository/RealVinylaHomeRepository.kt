package com.vinyla_android.data.repository

import com.vinyla_android.data.source.VinylaHomeSource
import com.vinyla_android.domain.entity.VinylHome
import com.vinyla_android.domain.repository.VinylaHomeRepository
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 11, 2021
 */

internal class RealVinylaHomeRepository @Inject constructor(
    private val localVinylaHomeSource: VinylaHomeSource,
    private val remoteVinylaHomeSource: VinylaHomeSource,
) : VinylaHomeRepository {

    override suspend fun getVinylHome(): VinylHome {
        return remoteVinylaHomeSource.getVinylHome()
    }

    override suspend fun saveVinylHome() {
        localVinylaHomeSource.saveVinylHome()
    }
}
