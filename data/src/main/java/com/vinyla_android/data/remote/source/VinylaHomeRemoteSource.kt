package com.vinyla_android.data.remote.source

import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.source.VinylaHomeSource
import com.vinyla_android.domain.entity.VinylHome
import javax.inject.Inject

/**
 * Created By Malibin
 * on 7월 26, 2021
 */

internal class VinylaHomeRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylaHomeSource {

    override suspend fun getVinylHome(): VinylHome {
        val response = vinylaService.getHomeInfo()
        return response.body()?.toVinylHome() ?: error("server error")
    }

    override suspend fun saveVinylHome() {
        throw UnsupportedOperationException("remote saveVinylHome cannot be called")
    }
}
